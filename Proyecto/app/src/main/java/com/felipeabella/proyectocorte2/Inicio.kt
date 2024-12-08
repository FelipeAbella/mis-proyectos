package com.felipeabella.proyectocorte2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast

class Inicio : AppCompatActivity() {

    private lateinit var btnCalculadora: Button
    private lateinit var btnCamara: Button
    private lateinit var btnUbicacion: Button
    private lateinit var btnChat: Button
    private lateinit var btnJuego: Button
    private lateinit var btnPerfil: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)

        // Busca el objeto en la pantalla - layout
        btnCalculadora = findViewById(R.id.btn_calc)
        btnJuego = findViewById(R.id.btn_juego)
        btnCamara = findViewById(R.id.btn_cam)
        btnUbicacion = findViewById(R.id.btn_ubi)
        btnChat = findViewById(R.id.btn_chat)
        btnPerfil = findViewById(R.id.btn_perfil)

        // Configura el listener para el botón
        btnCalculadora.setOnClickListener {
            val intent = Intent(this, Calculadora::class.java)
            startActivity(intent)
        }

        btnJuego.setOnClickListener {
            val intent = Intent(this, snakeMain::class.java)
            startActivity(intent)
        }

        btnCamara.setOnClickListener {
            val intent = Intent(this, Camara::class.java)
            startActivity(intent)
        }

        btnUbicacion.setOnClickListener {
            val intent = Intent(this, Ubicacion::class.java)
            startActivity(intent)
        }

        btnChat.setOnClickListener {
            val intent = Intent(this, usersList::class.java)
            startActivity(intent)
        }

        btnPerfil.setOnClickListener {
            mAuth = FirebaseAuth.getInstance()
            if (mAuth.currentUser?.uid == "USLojC7jnnQNyERcCIhXROr6AM53"){
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
                } else {
                Toast.makeText(this, "No tienes el flow para entrar aca", Toast.LENGTH_SHORT).show()
            }
        }
    }
}