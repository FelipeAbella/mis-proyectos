package com.felipeabella.calendarioeventos

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felipeabella.calendarioeventos.databinding.ActivityMainBinding
import java.io.File
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.graphics.Matrix
import android.media.ExifInterface

class MainActivity : AppCompatActivity() {

    private lateinit var postAdapter: PostAdapter
    private val posts = mutableListOf<Post>()
    private lateinit var file: File
    private lateinit var imgView: ImageView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextPost: EditText = binding.editTextPost
        val buttonPost: Button = binding.buttonPost
        val recyclerViewPosts: RecyclerView = binding.recyclerViewPosts
        imgView = binding.img

        postAdapter = PostAdapter(posts)
        recyclerViewPosts.layoutManager = LinearLayoutManager(this)
        recyclerViewPosts.adapter = postAdapter

        buttonPost.setOnClickListener {
            val newPostContent = editTextPost.text.toString()
            if (newPostContent.isNotEmpty()) {
                val newPost = Post(
                    content = newPostContent,
                    date = getCurrentDate(),
                    imageUri = if (::file.isInitialized) Uri.fromFile(file) else null
                )
                posts.add(newPost)
                postAdapter.notifyDataSetChanged()
                editTextPost.text.clear()
                registerPostInCalendar(newPost)
            }
        }

        binding.buttonCalendar.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        binding.openCamera.setOnClickListener {
            launchCamera()
        }

        binding.saveToGallery.setOnClickListener {
            if (!::file.isInitialized) {
                Toast.makeText(this, "Toma una foto antes bro", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                Toast.makeText(this, "Guardando", Toast.LENGTH_SHORT).show()
                saveToGallery()
            }
        }
    }

    private val openCameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                if (::file.isInitialized && file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    val rotatedBitmap = rotateImageIfRequired(bitmap, file)
                    imgView.setImageBitmap(rotatedBitmap)
                    imgView.invalidate()
                } else {
                    Toast.makeText(this, "No se encontró el archivo de la imagen", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Error al capturar la imagen", Toast.LENGTH_SHORT).show()
            }
        }

    private fun rotateImageIfRequired(image: Bitmap, selectedImage: File): Bitmap {
        val ei = ExifInterface(selectedImage.absolutePath)
        val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(image, 90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(image, 180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(image, 270f)
            else -> image //No es necesario rotar la imagen
        }
    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix().apply { postRotate(angle) }
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    private fun launchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            createPhotoFile()
            val photoUri: Uri = FileProvider.getUriForFile(
                this,
                "${BuildConfig.APPLICATION_ID}.fileprovider",
                file
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            openCameraLauncher.launch(intent)
        }
    }

    private fun createPhotoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", dir)
    }

    private fun saveToGallery() {
        val content = createContentValues()
        val uri = saveImageToGallery(content)
        clearContentValues(content, uri)
    }

    private fun createContentValues(): ContentValues {
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, file.name)
            put(MediaStore.Files.FileColumns.MIME_TYPE, "image/jpg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }
    }

    private fun saveImageToGallery(content: ContentValues): Uri? {
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
        if (uri != null) {
            contentResolver.openOutputStream(uri)?.use { outputStream ->
                val originalBitmap = BitmapFactory.decodeFile(file.absolutePath)
                val rotatedBitmap = rotateImageIfRequired(originalBitmap, file)
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
        }
        return uri
    }

    private fun clearContentValues(content: ContentValues, uri: Uri?) {
        uri?.let {
            content.clear()
            content.put(MediaStore.MediaColumns.IS_PENDING, 0)
            contentResolver.update(it, content, null, null)
            Toast.makeText(this, "Imagen guardada en la galería", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(this, "Error: No se pudo guardar la imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun registerPostInCalendar(post: Post) {
        Toast.makeText(this, "Post registrado: ${post.date}", Toast.LENGTH_SHORT).show()
    }
}

data class Post(
    val content: String = "",
    val date: String = "",
    val imageUri: Uri? = null
    //val imageURL: String="", //aquí guarda la url de firebase
    //var likes: Int = 0
    //var dislikes: Int = 0
)