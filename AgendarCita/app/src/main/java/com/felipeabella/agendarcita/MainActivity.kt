package com.felipeabella.agendarcita

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import java.util.Calendar
import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private var appointmentID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        //boton para agendar cita
        val scheduleButton: Button =
            findViewById(R.id.scheduleButton) //Boton que saca el calendario
        scheduleButton.setOnClickListener {
            scheduleAppointment()
        }
    }

    data class Appointment(val id: Int, val dateTime: String)

    private fun scheduleAppointment() {
        val emailField: EditText = findViewById(R.id.emailField1)//campos de texto en el layout
        val emailField2: EditText = findViewById(R.id.emailField2)//campos de texto en el layout
        val email1 = emailField.text.toString()//Toma el valor de los campos de texto
        val email2 = emailField2.text.toString()
        val calendar = Calendar.getInstance()

        DatePickerDialog(
            this,
            { _, year, month, day ->
                TimePickerDialog(this, { _, hourOfDay, minute ->
                    val appointmentTime = "$year-${month + 1}--$day $hourOfDay:$minute"

                    //crear la cita con un ID unico
                    val appointment = Appointment(appointmentID++, appointmentTime)
                    //guardar la cita en firebase
                    saveAppointmentToFirebase(appointment)
                    //enviar el correo con la información de la cita
                    val recipientEmail = email1
                    val subject = "Cita programada"
                    val body =
                        "Señor usuario tu cita ha guardadp asignada el $appointmentTime, con el codigo $appointment."

                    sendEmailInBackground(recipientEmail, subject, body)
                    //sendEmail(appointment,email1,email2)
                    //Mostrar detalles en pantalla
                    val appointmentDetails: TextView = findViewById(R.id.appointmentDetails)
                    appointmentDetails.text =
                        "Cita agendada para $appointmentTime con ID: ${appointment.id}"
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun sendEmailInBackground(recipientEmail: String, subject: String, body: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                sendEmail(recipientEmail, subject, body)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun sendEmail(recipientEmail: String, subject: String, body: String) {
        withContext(Dispatchers.IO) {
            val props = java.util.Properties()
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.starttls.enable"] = "true"
            props["mail.smtp.host"] = "smtp.gmail.com" //cambia segun el proveedor de tu email
            props["mail.smtp.port"] = "587"

            val session = Session.getInstance(props, object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication("citaspruebas0@gmail.com", "unki wxps olgt uulq")
                }
            })
            //https://support.google.com/mail/answer/185833?hl=es-409-documentation
            //es importante para crear la key de enviar
            try {

                val message = MimeMessage(session)
                message.setFrom(InternetAddress("citaspruebas0@gmail.com"))
                message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail)
                )
                message.subject = subject
                message.setText(body)
                Transport.send(message)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun saveAppointmentToFirebase(appointment: Appointment) {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("citas")

        val appointmentData = mapOf(
            "id" to appointment.id,
            "dateTime" to appointment.dateTime
        )
        ref.child(appointment.id.toString()).setValue(appointmentData)
            .addOnSuccessListener {
                Toast.makeText(this, "Cita programada een Firebase", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al guardar la cita", Toast.LENGTH_SHORT).show()
            }
    }

    private fun fetchAppointmentsFromFirebase() {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("citas")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (appointmentSnapshot in snapshot.children
                ) {
                    val appointment = appointmentSnapshot.getValue(
                        Appointment::class.java
                    )
                    //aqui puedes mostrat las citas obtenidas en tu interfaz de usuario
                    //por ejemplo agregar la cita a una lista o actualizar una vista
                    if (appointment != null) {
                        Log.d("Firebase", "Cita obtenida: ${appointment.dateTime}")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error al obtener las citas", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}