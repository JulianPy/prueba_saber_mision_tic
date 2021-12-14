package com.example.saberpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class plantilla_ingreso : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plantilla_ingreso)

        // Activar servicio de autentificación
        auth = Firebase.auth

        // Boton para cerrar sesion
        var sesion: Button = findViewById(R.id.sesion)
        sesion.setOnClickListener{
            closeSesion()
        }

        // Ingreso al menu biologia
        var menu_biologia:ImageView = findViewById(R.id.biologia)
        menu_biologia.setOnClickListener{
            var intent = Intent(this, MenuBiologia::class.java)
            startActivity(intent)
        }
        }

// -------------------------------------- Funciones de apoyo ---------------------------------------
    private fun closeSesion(){
        Firebase.auth.signOut()
        var intento3 = Intent(this, Inicio_sesion::class.java)
        startActivity(intento3)
    }
}

