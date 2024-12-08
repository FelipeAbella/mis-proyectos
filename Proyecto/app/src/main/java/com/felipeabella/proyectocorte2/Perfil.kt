package com.felipeabella.proyectocorte2

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class Perfil : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var btnSelectImage: Button
    private lateinit var btnSave: Button
    private lateinit var edtName: EditText
    private lateinit var edtPhone: EditText
    private var imageUri: Uri? = null
    private lateinit var linearLayoutImages: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Inicializar vistas
        imageView = findViewById(R.id.imageView)
        btnSelectImage = findViewById(R.id.btnSelectImage)
        btnSave = findViewById(R.id.btnSave)
        edtName = findViewById(R.id.edtName)
        edtPhone = findViewById(R.id.edtPhone)
        linearLayoutImages = findViewById(R.id.linearLayoutImages)

        // Configurar el listener para seleccionar imagen
        btnSelectImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()  // Permitir recorte de la imagen
                .start()
        }

        // Configurar el listener para guardar datos
        btnSave.setOnClickListener {
            saveDataToFirebase()
        }

        // Cargar los datos existentes desde Firebase
        loadDataFromFirebase()

        //Lista de URLs de las imágenes
        val imageURLs = listOf(
            "https://s1.significados.com/foto/imagen-de-destaque-que-es-el-futbol.-jugadores-y-balon.jpg",
            "https://hiraoka.com.pe/media/mageplaza/blog/post/m/e/mejores-televisores-para-ver-futbol-en-casa.jpg",
            "https://industriamusical.com/wp-content/uploads/2022/12/Spotify.jpg",
            "https://okdiario.com/img/2022/01/05/6-buenas-razones-para-jugar-a-los-videojuegos.jpg")

        //Cargar las imágenes en el ScrollView
        loadImagesInScrollView(imageURLs)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == ImagePicker.REQUEST_CODE) {
            imageUri = data?.data
            imageView.setImageURI(imageUri) // Mostrar la imagen seleccionada
        }
    }

    //Función para validar los campos de entrada.
    //Retorna true si todos los campos son válidos, de lo contrario muestra un Toast y retorna false
    private fun validateInputs(): Boolean {
        val name = edtName.text.toString().trim()
        val phone = edtPhone.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if (phone.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu teléfono", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    //Función para guardar los datos del usuario en Firebase.
    //Incluye validación de campos y muestra un indicador de progreso durante la operación
    private fun saveDataToFirebase() {
        if (!validateInputs()) {
            return // Si la validación falla, no continuar
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val databaseRef = FirebaseDatabase.getInstance().getReference("Perfiles")
        val storageRef = FirebaseStorage.getInstance().getReference("images/$userId.jpg")

        // Crear y mostrar el ProgressDialog
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Guardando datos...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        imageUri?.let { uri ->
            storageRef.putFile(uri)
                .addOnSuccessListener {
                    // Obtener la URL de descarga de la imagen
                    storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                        // Crear un mapa con la información del usuario
                        val userInfo = mapOf(
                            "name" to edtName.text.toString(),
                            "phone" to edtPhone.text.toString(),
                            "image" to downloadUri.toString()
                        )
                        // Guardar la información en la base de datos
                        databaseRef.child(userId).setValue(userInfo)
                            .addOnSuccessListener {
                                progressDialog.dismiss() // Ocultar el ProgressDialog
                                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {
                                progressDialog.dismiss() // Ocultar el ProgressDialog
                                Toast.makeText(this, "Error al guardar datos", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener {
                    progressDialog.dismiss() // Ocultar el ProgressDialog
                    Toast.makeText(this, "Error al subir la imagen", Toast.LENGTH_SHORT).show()
                }
        } ?: run {
            // Si no hay imagen seleccionada, guardar solo los datos de texto
            val userInfo = mapOf(
                "name" to edtName.text.toString(),
                "phone" to edtPhone.text.toString(),
                "image" to ""
            )
            databaseRef.child(userId).setValue(userInfo)
                .addOnSuccessListener {
                    progressDialog.dismiss() // Ocultar el ProgressDialog
                    Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    progressDialog.dismiss() // Ocultar el ProgressDialog
                    Toast.makeText(this, "Error al guardar datos", Toast.LENGTH_SHORT).show()
                }
        }
    }

    //Función para cargar los datos del usuario desde Firebase.
    //Incluye la carga de imagen utilizando Glide y rellena los campos de texto.
    private fun loadDataFromFirebase() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val databaseRef = FirebaseDatabase.getInstance().getReference("Perfiles").child(userId)

        databaseRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val name = snapshot.child("name").value.toString()
                val phone = snapshot.child("phone").value.toString()
                val imageUrl = snapshot.child("image").value.toString()

                edtName.setText(name) // Rellenar el campo de nombre
                edtPhone.setText(phone) // Rellenar el campo de teléfono

                if (imageUrl.isNotEmpty()) {
                    Glide.with(this).load(imageUrl).into(imageView) // Cargar la imagen usando Glide
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error al cargar datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadImagesInScrollView(imageUrls: List<String>) {
        for (imageUrl in imageUrls) {
            val imageView = ImageView(this)
            // Establecer los parámetros de diseño para ajustar las imágenes a un tamaño fijo
            val params = LinearLayout.LayoutParams(
                400,
                300
            )
            imageView.layoutParams = params
            // Ajustar la imagen para que se vea completa
            imageView.adjustViewBounds = true
            //imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            // Usar Glide para cargar la imagen desde la URL
            Glide.with(this)
                .load(imageUrl)
                .override(400, 300) // Establecer dimensiones fijas para evitar problemas de Glide
                .into(imageView)
            // Añadir el ImageView al LinearLayout
            linearLayoutImages.addView(imageView)
        }
    }
}
