package com.miempresa.cot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_auth.txtpassword
import kotlinx.android.synthetic.main.activity_auth.txtusuario
import kotlinx.android.synthetic.main.activity_newaccount.*
import org.json.JSONException
import org.json.JSONObject

class newaccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newaccount)

        val policy= StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        btnregistrar.setOnClickListener(){
            newaccount()
        }

        btnvolverauth.setOnClickListener(){
            startActivity(Intent(this,authactivity::class.java))
            finish()
        }
    }
    fun newaccount(){
        var newusuario = txtusuario.text.toString()
        var newapellidos = txtapellidos.text.toString()
        var newemail = txtEmail.text.toString()
        var newestado = txtestado.text.toString()
        var newsexo = txtsexo.text.toString()
        var newdireccion = txtdireccion.text.toString()
        var newtelefono = txttelefono.text.toString()
        var newpassword = txtpassword.text.toString()
        if (newusuario.isNotEmpty() && newpassword.isNotEmpty()){
            if (checkBox.isChecked){
                val queue = Volley.newRequestQueue(this)
                val json = JSONObject()
                var url= getString(R.string.urlAPI)+"/api/usuarios"
                json.put("nombre",newusuario)
                json.put("apellidos",newapellidos)
                json.put("email",newemail)
                json.put("estado",newestado)
                json.put("sexo",newsexo)
                json.put("direccion",newdireccion)
                json.put("telefono",newtelefono)
                json.put("clave",newpassword)
                val jsonArray = JsonObjectRequest(
                    Request.Method.POST,url,json,
                    Response.Listener{ response ->
                        try{
                            startActivity(Intent(this,temperactivity::class.java))
                            finish()
                        }catch (e: JSONException){
                            Toast.makeText(
                                applicationContext,"Intentelo mas Tarde",
                                Toast.LENGTH_LONG).show()
                        }
                    },
                    Response.ErrorListener{
                        Toast.makeText(applicationContext,"Comprueba que tiene acceso a internet: ",
                            Toast.LENGTH_LONG).show()
                    })
                queue.add(jsonArray)
            }else{
                Toast.makeText(
                    applicationContext,"Acepte los Terminos y Condiciones", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(
                applicationContext,"Complete todos los campos requeridos", Toast.LENGTH_LONG).show()
        }
    }
}