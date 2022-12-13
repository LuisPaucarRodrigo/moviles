package com.miempresa.cot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_auth.*
import org.json.JSONException

class authactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val policy= StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        btnlogear.setOnClickListener(){
            logear()
        }

        btnRegister.setOnClickListener(){
            val register = Intent(this,newaccount::class.java)
            startActivity(register)
        }

        btngoogle.setOnClickListener(){
            Toast.makeText(
                applicationContext,
                "Logueo por google ",
                Toast.LENGTH_LONG
            ).show()
        }

        btnfacebook.setOnClickListener(){
            Toast.makeText(
                applicationContext,
                "Logueo por facebook ",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    fun logear(){
        var usuario = txtusuario.text
        var password = txtpassword.text
        if (usuario.isEmpty() && password.isNotEmpty()){
            Toast.makeText(
                applicationContext,
                "Complete Usuario",
                Toast.LENGTH_LONG
            ).show()
        };if(usuario.isNotEmpty() && password.isEmpty()){
            Toast.makeText(
                applicationContext,
                "Complete Password ",
                Toast.LENGTH_LONG
            ).show()
        };if (usuario.isNotEmpty() && password.isNotEmpty()){
            val queue = Volley.newRequestQueue(this)
            var url = getString(R.string.urlAPI)+"/api/usuario/"
            url = url + usuario.toString() + "/"+ password.toString()
            val stringRequest = JsonObjectRequest(url,
                Response.Listener { response ->
                    try {
                        val valor = response
                        var temperactivity = Intent(this,temperactivity::class.java)
                        temperactivity.putExtra("id",valor.getString("id"))
                        startActivity(temperactivity)
                        finish()
                    } catch (e: JSONException) {
                        Toast.makeText(
                            applicationContext,
                            "Usuario y Password incorrectos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(
                        applicationContext,
                        "Compruebe que tiene acceso a internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }else{
            Toast.makeText(
                applicationContext,
                "Complete Usuario y Password ",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}