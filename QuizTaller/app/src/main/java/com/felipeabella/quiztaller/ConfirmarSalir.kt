package com.felipeabella.quiztaller

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ConfirmarSalir : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmar_salir)

        val botonSi: Button = findViewById(R.id.btnSi)
        val botonNo: Button = findViewById(R.id.btnNo)

        botonSi.setOnClickListener {
            finishAffinity()
        }

        botonNo.setOnClickListener {
            finish()
        }
    }
}
