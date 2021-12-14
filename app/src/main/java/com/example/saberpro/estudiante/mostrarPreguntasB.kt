package com.example.saberpro.estudiante

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.saberpro.R
import com.example.saberpro.modeloB.listado_biologia
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class mostrarPreguntasB : AppCompatActivity() {

    // Inicializar autentificación
    var auth = Firebase.auth

    // Conexión base de datos
    val database = Firebase.database
    val dbReference = database.getReference("Preguntas_Biologia")

    // Crear variables para conectar la lista de preguntas con la vista
    private lateinit var listaPreguntas_B:ArrayList<listado_biologia>
    private lateinit var listaPreguntas_B_Adaprter: ArrayAdapter<listado_biologia>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_preguntas_b)

        // Inicializar la lista en vacio para evitar problemas
        listaPreguntas_B = ArrayList<listado_biologia>()

        mostrarPReguntas()

        var lvPreguntas:ListView = findViewById(R.id.preguntas_biologia)

        lvPreguntas.setOnItemClickListener{parent,view, position, id ->

            var pelicula = listaPreguntas_B[position]

            Toast.makeText(this, "respuesta ${pelicula.respuesta}", Toast.LENGTH_LONG).show()
        }
    }

    fun mostrarPReguntas(){
        var bioligia_item_listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (pre in snapshot.children){
                    // Mapeamos lo que está en firebase en un  objeto mapa
                    var mapBiologia: Map<String, Any> = pre.value as Map<String, Any>

                    // Guardamos en un objeto listado_biologia la información que se encuenta en Firebae
                    var pregunta:listado_biologia = listado_biologia("", "",
                    "", "","", "","")

                    pregunta.id = mapBiologia.get("id").toString()
                    pregunta.pregunta = mapBiologia.get("pregunta").toString()
                    pregunta.opcion1 = mapBiologia.get("opcion1").toString()
                    pregunta.opcion2 = mapBiologia.get("opcion2").toString()
                    pregunta.opcion3 = mapBiologia.get("opcion3").toString()
                    pregunta.opcion4 = mapBiologia.get("opcion4").toString()
                    pregunta.respuesta = mapBiologia.get("respuesta").toString()

                    // Adicionamos lo que llega de Firebase a la variable listaPreguntas_B
                    listaPreguntas_B.add(pregunta)

                    listaPreguntas_B_Adaprter = biologiaAdapter(this@mostrarPreguntasB,
                        listaPreguntas_B )

                    var lista_actividad:ListView=findViewById(R.id.preguntas_biologia)
                    lista_actividad.adapter = listaPreguntas_B_Adaprter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        // Adicionamos el evento a cada una de las preguntas de Biologia
        dbReference.addValueEventListener(bioligia_item_listener)
    }
}