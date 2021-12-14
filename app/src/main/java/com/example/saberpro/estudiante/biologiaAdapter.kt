package com.example.saberpro.estudiante

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.saberpro.R
import com.example.saberpro.modeloB.listado_biologia

class biologiaAdapter(private val nContext: Context, val listaBiologia: List<listado_biologia>)
    : ArrayAdapter<listado_biologia>(nContext,0, listaBiologia){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup):View {

        val layout = LayoutInflater.from(nContext).inflate(R.layout.biologia_items, parent, false)
        val pre_bio = listaBiologia[position]

        val tvid = layout.findViewById<TextView>(R.id.id_pregunta)
        val tvpregunta = layout.findViewById<TextView>(R.id.pregunta)
        val tvopcion_a = layout.findViewById<TextView>(R.id.opcion_a)
        val tvopcion_b = layout.findViewById<TextView>(R.id.opcion_b)
        val tvopcion_c = layout.findViewById<TextView>(R.id.opcion_c)
        val tvopcion_d = layout.findViewById<TextView>(R.id.opcion_d)
        val tvresp = layout.findViewById<TextView>(R.id.respuesta)

        tvid.text = pre_bio.id
        tvpregunta.text = pre_bio.pregunta
        tvopcion_a.text = pre_bio.opcion1
        tvopcion_b.text = pre_bio.opcion2
        tvopcion_c.text = pre_bio.opcion3
        tvopcion_d.text = pre_bio.opcion4
        tvresp.text = pre_bio.respuesta


        return layout
        }
}