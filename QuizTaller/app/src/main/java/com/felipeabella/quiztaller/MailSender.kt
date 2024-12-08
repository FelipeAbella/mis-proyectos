package com.felipeabella.quiztaller

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import java.util.Properties

class MailSender(
    private val context: Context,
    private val emailDestino: String,
    private val nuevaContrasena: String
) {
    suspend fun enviarCorreo(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val properties = Properties().apply {
                    put("mail.smtp.host", "smtp.gmail.com")
                    put("mail.smtp.port", "587")
                    put("mail.smtp.auth", "true")
                    put("mail.smtp.starttls.enable", "true")
                }

                val session = Session.getInstance(properties, object : javax.mail.Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication("abellafelipe07@gmail.com", "uvkd xucy hwby iqxy")
                    }
                })

                val message = MimeMessage(session).apply {
                    setFrom(InternetAddress("abellafelipe07@gmail.com"))
                    setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino))
                    subject = "Reestablecimiento de contraseña"
                    setText("Su nueva contraseña es: $nuevaContrasena")
                }

                enviarNuevaContrasena(nuevaContrasena)

                Transport.send(message)
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    private fun enviarNuevaContrasena(nuevaContrasena: String) {
        val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        sharedPreferences.edit().putString("contrasenaGuardada", nuevaContrasena).apply()
    }
}
