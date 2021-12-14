package com.example.saberpro.profesor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.saberpro.MenuBiologia
import com.example.saberpro.R
import com.example.saberpro.modeloB.listado_biologia
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import java.util.*

class agregarPregunta : AppCompatActivity() {

    val database = Firebase.database
    val dbReference = database.getReference("Preguntas_Biologia")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_pregunta)

        // Inicializar la base de datos
        Firebase.initialize(this)

        var boton_agregar: Button = findViewById(R.id.agregar_pregunta)
        boton_agregar.setOnClickListener{
            extraerInfo()
            val dialog = AlertDialog.Builder(this).setTitle("Buen trabajo !")
                .setMessage ("la pregunta se agregó correctamente").create().show()
            var intent = Intent(this, MenuBiologia::class.java)
            startActivity(intent)
        }

    }

    private fun extraerInfo(){

        var editPregunta: EditText = findViewById(R.id.pregunta)
        var editOpcionA: EditText = findViewById(R.id.opcion_a)
        var editOpcionB: EditText = findViewById(R.id.opcion_b)
        var editOpcionC: EditText = findViewById(R.id.opcion_c)
        var editOpcionD: EditText = findViewById(R.id.opcion_d)
        var editRespuesta: EditText = findViewById(R.id.respuesta)

        var pregunta:String = editPregunta.getText().toString()
        var opcionA:String = editOpcionA.getText().toString()
        var opcionB:String = editOpcionB.getText().toString()
        var opcionC:String = editOpcionC.getText().toString()
        var opcionD:String = editOpcionD.getText().toString()
        var respuesta:String = editRespuesta.getText().toString()

        var objeto_pregunta = listado_biologia(
            UUID.randomUUID().toString(),
            pregunta,
            opcionA,
            opcionB,
            opcionC,
            opcionD,
            respuesta
        )

        // Agregar a la base de datos
        dbReference.child(objeto_pregunta.id).setValue(objeto_pregunta)
    }
}

