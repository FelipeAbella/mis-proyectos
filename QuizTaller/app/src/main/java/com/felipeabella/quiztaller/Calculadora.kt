package com.felipeabella.quiztaller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Calculadora : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calc)

        val btnSuma: Button = findViewById(R.id.btnSuma)
        val btnMultiplicacion: Button = findViewById(R.id.btnMultiplicar)
        val btnDivision: Button = findViewById(R.id.btnDividir)
        val btnSalir: Button = findViewById(R.id.btnSalir)

        // Cargar pantalla de Suma
        btnSuma.setOnClickListener {
            setContentView(R.layout.activity_calc_suma)
            val etNumero1: EditText = findViewById(R.id.etNum1)
            val etNumero2: EditText = findViewById(R.id.etNum2)
            val tvResultado: TextView = findViewById(R.id.resultadoSuma)
            val btnSumar: Button = findViewById(R.id.btnSumar)
            val btnVolver: Button = findViewById(R.id.btnVolver)

            btnSumar.setOnClickListener {
                val num1 = etNumero1.text.toString().toDoubleOrNull() ?: 0.0
                val num2 = etNumero2.text.toString().toDoubleOrNull() ?: 0.0
                tvResultado.text = "Resultado: ${num1 + num2}"
            }

            btnVolver.setOnClickListener{
                val intent = Intent(this, Calculadora::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Cargar pantalla de Multiplicación
        btnMultiplicacion.setOnClickListener {
            setContentView(R.layout.activity_calc_mult)
            val etNumero1: EditText = findViewById(R.id.etNum1)
            val etNumero2: EditText = findViewById(R.id.etNum2)
            val tvResultado: TextView = findViewById(R.id.resultadoMult)
            val btnMultiplicar: Button = findViewById(R.id.btnMultiplicar)
            val btnVolver: Button = findViewById(R.id.btnVolver)

            btnMultiplicar.setOnClickListener {
                val num1 = etNumero1.text.toString().toDoubleOrNull() ?: 1.0
                val num2 = etNumero2.text.toString().toDoubleOrNull() ?: 1.0
                tvResultado.text = "Resultado: ${num1 * num2}"
            }

            btnVolver.setOnClickListener{
                val intent = Intent(this, Calculadora::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Cargar pantalla de División
        btnDivision.setOnClickListener {
            setContentView(R.layout.activity_calc_div)
            val etNumero1: EditText = findViewById(R.id.etNum1)
            val etNumero2: EditText = findViewById(R.id.etNum2)
            val tvResultado: TextView = findViewById(R.id.resultadoDiv)
            val btnDividir: Button = findViewById(R.id.btnDividir)
            val btnVolver: Button = findViewById(R.id.btnVolver)

            btnDividir.setOnClickListener {
                val num1 = etNumero1.text.toString().toDoubleOrNull() ?: 0.0
                val num2 = etNumero2.text.toString().toDoubleOrNull() ?: 1.0 // Evita división por cero
                tvResultado.text = "Resultado: ${num1 / num2}"
            }

            btnVolver.setOnClickListener{
                val intent = Intent(this, Calculadora::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Acción para el botón de Salir
        btnSalir.setOnClickListener {
            setContentView(R.layout.activity_confirmar_salir)
            val botonSi: Button = findViewById(R.id.btnSi)
            val botonNo: Button = findViewById(R.id.btnNo)

            botonSi.setOnClickListener {
                finishAffinity()
            }

            botonNo.setOnClickListener {
                val intent = Intent(this, Calculadora::class.java)
                startActivity(intent)
            }
        }
    }
}