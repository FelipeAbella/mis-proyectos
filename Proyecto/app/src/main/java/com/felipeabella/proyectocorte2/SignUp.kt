package com.felipeabella.proyectocorte2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        editName = findViewById(R.id.edt_name)
        editEmail = findViewById(R.id.edt_email)
        editPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btn_Reg)

        btnSignUp.setOnClickListener {
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            signUp(name,email,password)
        }
    }

    private fun signUp(name:String, email:String, password:String) {
        //Lógica para crear un usuario
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    //Sirve para cambiar la pantalla
                    val intent = Intent(this@SignUp, Inicio::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@SignUp,
                        "Ha ocurrido un error",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    private fun addUserToDatabase(name:String, email:String, uid:String) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))

    }

}