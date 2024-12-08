package com.felipeabella.proyectocorte2

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.felipeabella.proyectocorte2.R
import java.io.File
import java.io.OutputStream
import com.felipeabella.proyectocorte2.databinding.ActivityCamaraBinding

class Camara : AppCompatActivity() {
    private lateinit var  binding: ActivityCamaraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamaraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.openCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { component ->
                    createPhotoFile()
                    val photoUri: Uri =
                        FileProvider.getUriForFile(
                            this,
                            BuildConfig.APPLICATION_ID + ".fileprovider", file
                        )
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            openCamera.launch(intent)
            //openCamera.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
        binding.saveToGallery.setOnClickListener {
            if(!::file.isInitialized){
                Toast.makeText(this,"Qué vas a guardar bro",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                saveToGallery()
                Toast.makeText(this,"Guardando",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val openCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Ya no usamos result.data, simplemente cargamos el archivo directamente
                if (::file.isInitialized && file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    binding.img.setImageBitmap(bitmap)  // Aquí cargamos la imagen en el ImageView
                    // Actualizamos la interfaz por si acaso
                    binding.img.invalidate()
                } else {
                    Toast.makeText(this, "No se encontró el archivo de la imagen", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Error al tomar la foto", Toast.LENGTH_SHORT).show()
            }
        }

    private lateinit var file: File
    private fun createPhotoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg",dir)
    }

    private fun saveToGallery() {
        val content = createContent()
        val uri = save(content)
        clearContents(content, uri)
        Toast.makeText(this,"Imagen guardada en la galeria",Toast.LENGTH_SHORT).show()
    }

    private fun createContent(): ContentValues {
        val fileName = file.name
        val fileType = "image/jpg"
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.Files.FileColumns.MIME_TYPE, fileType)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }
    }

    private fun save(content:ContentValues): Uri {
        var outputStream: OutputStream?
        var uri: Uri?
        application.contentResolver.also { resolver ->
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
            outputStream = resolver.openOutputStream(uri!!)
        }
        outputStream.use { output ->
            if(output != null) {
                getBitmap().compress(Bitmap.CompressFormat.JPEG,100,output)
            }
        }
        return uri!!
    }

    private fun clearContents(content:ContentValues, uri: Uri) {
        content.clear()
        content.put(MediaStore.MediaColumns.IS_PENDING,0)
        contentResolver.update(uri,content,null, null)
    }

    private fun getBitmap():Bitmap {
        return BitmapFactory.decodeFile(file.toString())
    }
}