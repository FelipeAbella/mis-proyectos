package com.felipeabella.proyectocorte2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    //Instanciar
    private lateinit var editEmail:EditText
    private lateinit var editPassword:EditText
    private lateinit var btnLogIn: Button
    private lateinit var btnSignUp:Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        //Busca el objeto en la pantalla - layout
        editEmail = findViewById(R.id.edtEmail)
        editPassword = findViewById(R.id.edtPassword)
        btnLogIn = findViewById(R.id.btn_Login)
        btnSignUp = findViewById(R.id.btn_Register)

        btnSignUp.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            Login(email,password)
        }
    }

    private fun Login(email:String, password:String) {
        //Login del usuario
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //Sirve para cambiar la pantalla
                    val intent = Intent(this,Inicio::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@LogIn,
                        "Error, el usuario no existe",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}