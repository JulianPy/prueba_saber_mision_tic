package com.example.saberpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Inicio_sesion : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        // Activar servicio de autentificación
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        // Informacion de campos Usuario/Email y Contraseña
        var usuario_inicio: EditText = findViewById(R.id.usuario)
        var contraseña_inicio: EditText = findViewById(R.id.contraseña)

        var boton: Button = findViewById(R.id.registro_1)
        var intento = Intent(this, MainActivity::class.java)
            boton.setOnClickListener{
                startActivity(intento)
            }
        // Botón de ingreso
        var boton2: Button = findViewById(R.id.ingresar)
        boton2.setOnClickListener{
            if ((usuario_inicio.getText().toString().isEmpty()) &&
                contraseña_inicio.getText().toString().isEmpty()){
                val dialog = AlertDialog.Builder(this).setTitle("ERROR!")
                    .setMessage ("Campos nulos, ingrese usuario y contraseña").create().show()
            }else{
                var email = usuario_inicio.getText().toString()
                var password = contraseña_inicio.getText().toString()
                singIn(email, password)
            }
            }
        }
// -------------------------------------- Funciones de apoyo ---------------------------------------

    // Metodo para ingresar el usuario
    private fun singIn(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    var intento2 = Intent(this, plantilla_ingreso::class.java)
                    startActivity(intento2)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Verificar si la sesión quedó abierta y llevarlo de una vez a la actividad de preguntas
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            var intento2 = Intent(this, plantilla_ingreso::class.java)
            startActivity(intento2)
        }
    }

}
