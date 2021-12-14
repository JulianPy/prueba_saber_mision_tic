package com.example.saberpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.saberpro.estudiante.mostrarPreguntasB
import com.example.saberpro.profesor.agregarPregunta

// Esta actividad es propia del rol del profesor

class MenuBiologia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_biologia)


        var boton_agregar: Button = findViewById(R.id.agregar_pregunta)
        boton_agregar.setOnClickListener{
            var intent = Intent(this, agregarPregunta::class.java)
            startActivity(intent)
        }

        var boton_ver: Button = findViewById(R.id.ver_preguntas)
        boton_ver.setOnClickListener{
            var intent2 = Intent(this, mostrarPreguntasB::class.java)
            startActivity(intent2)
        }
    }
}