package com.felipeabella.quiztaller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Random

class ReestablecerContra : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var btnAceptar: Button
    private lateinit var btnCancelar: Button
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reestablecer_contra)

        etEmail = findViewById(R.id.etEmailRecuperacion)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnCancelar = findViewById(R.id.btnCancelar)
        titleTextView = findViewById(R.id.Title)
        titleTextView.visibility = TextView.GONE

        btnCancelar.setOnClickListener { volverInicio() }

        btnAceptar.setOnClickListener {
            val nuevaContrasena = generarContrasenaAleatoria()
            enviarCorreo(nuevaContrasena)
        }
    }

    private fun generarContrasenaAleatoria(): String {
        val random = Random()
        return (100000 + random.nextInt(900000)).toString()
    }

    private fun enviarCorreo(nuevaContrasena: String) {
        val emailDestino = etEmail.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            val resultado = MailSender(this@ReestablecerContra, emailDestino, nuevaContrasena).enviarCorreo()
            launch(Dispatchers.Main) {
                if (resultado) {
                    Toast.makeText(this@ReestablecerContra, "Correo enviado correctamente.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ReestablecerContra, "Error al enviar el correo.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun volverInicio() {
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
        finish()
    }
}