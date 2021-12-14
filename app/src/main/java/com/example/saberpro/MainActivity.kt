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
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Activar servicio de autentificación
        auth = Firebase.auth


        // Validar correo
        fun validar_correo(email:String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        // Validar contraseña
        fun validar_contraseña(clave:String): Boolean {

            val minuscula = Pattern.compile("[a-z]")
            val mayuscula = Pattern.compile("[A-Z]")
            val numero = Pattern.compile("[0-9]")
            val especial = Pattern.compile("([@#%&])")

            return ((clave.length >= 8) &&
                    (minuscula.matcher(clave).find()) &&
                    (mayuscula.matcher(clave).find()) &&
                    (numero.matcher(clave).find()) &&
                    (especial.matcher(clave).find()))
        }
        // Variables
        var nombre: EditText = findViewById(R.id.nombre) // Nombre
        var apellido: EditText = findViewById(R.id.apellido) // Apellidos
        var correo: EditText = findViewById(R.id.correo) // Correo
        var contraseña: EditText = findViewById(R.id.clave) // Contraseña
        var telefono: EditText = findViewById(R.id.telefono) // Telefono
        var boton_login: Button = findViewById(R.id.login) // Botón

        var conteo1:Int = 0
        var conteo2:Int= 0

        // Validaciones
        boton_login.setOnClickListener {
            // Validación Nombre
            if (nombre.getText().toString().isEmpty()){
                nombre.setError("Campo requerido")
            }
            // Validación Apellido
            if (apellido.getText().toString().isEmpty()){
                apellido.setError("Campo requerido")
            }
            // Validación Correo
            if (correo.getText().toString().isEmpty()){
                correo.setError("Campo requerido")
            }else{
                if(validar_correo(correo.getText().toString())){
                    conteo1 = 1
                }else{
                    correo.setError("Digite un correo válido")
                    conteo1 = 0
                }
            }
            // Validación Contraseña
            if (contraseña.getText().toString().isEmpty()){
                contraseña.setError("Campo requerido")
            }else{
                if(validar_contraseña(contraseña.getText().toString())){
                    conteo2 = 1
                } else{
                    contraseña.setError("se requeire: \n * Mínimo un número \n " +
                            "* Mínimo una letra mayúscula \n " +
                            "* La clave debe ser ser de 8 caracteres\n " +
                            "* Debe tener mínimo un caracter especial")
                    conteo2 = 0
                }
            }
            // Validación Telefono
            if (telefono.getText().toString().isEmpty()){
                telefono.setError("Campo requerido")
            }
            // Registro
            var email = correo.getText().toString()
            var password = contraseña.getText().toString()
            if((conteo1 == 1) && (conteo2 == 1)){
                println(email)
                println(password)
                createAccount(email, password)
            }
        }
    }
// ------------------------------------- Funciones de apoyo ---------------------------------------
    // Método para crear una cuenta
    private fun createAccount(email:String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val dialog = AlertDialog.Builder(this).setTitle("Buen trabajo !")
                        .setMessage ("El usuario se registró correctamente").create().show()
                    var intento2 = Intent(this, Inicio_sesion::class.java)
                    startActivity(intento2)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    }

