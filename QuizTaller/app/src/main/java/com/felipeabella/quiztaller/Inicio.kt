package com.felipeabella.quiztaller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Inicio : AppCompatActivity() {

    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRestablecer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnlogin)
        btnRestablecer = findViewById(R.id.btnReestablecer)

        btnRestablecer.isEnabled = false

        btnLogin.setOnClickListener { verificaContra() }
        btnRestablecer.setOnClickListener { reestablecerContra() }
    }

    private fun verificaContra() {
        val passwordIngresada = etPassword.text.toString()
        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        val contrasenaGuardada = sharedPreferences.getString("contrasenaGuardada", "default_password") ?: "default_password"

        // Define una contraseña que siempre funcione
        val contrasenaMaestra = "12345678"

        if (passwordIngresada == contrasenaGuardada || passwordIngresada == contrasenaMaestra) {
            val intent = Intent(this, Calculadora::class.java)
            startActivity(intent)
            finish()
        } else {
            incrementarIntentosFallidos()
        }
    }

    private fun enviarNuevaContrasena(nuevaContrasena: String) {
        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        sharedPreferences.edit().putString("contrasenaGuardada", nuevaContrasena).apply()
    }

    private fun incrementarIntentosFallidos() {
        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val fallos = sharedPreferences.getInt("fallos", 0) + 1
        sharedPreferences.edit().putInt("fallos", fallos).apply()

        Toast.makeText(this, "Contraseña incorrecta. Intento $fallos de 3", Toast.LENGTH_SHORT).show()
        if (fallos >= 3) {
            Toast.makeText(this, "Por favor, reestablezca su contraseña.", Toast.LENGTH_SHORT).show()
            btnRestablecer.isEnabled = true
        }
    }

    private fun reestablecerContra() {
        val intent = Intent(this, ReestablecerContra::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        getSharedPreferences("AppPrefs", Context.MODE_PRIVATE).edit().putInt("fallos", 0).apply()
    }
}