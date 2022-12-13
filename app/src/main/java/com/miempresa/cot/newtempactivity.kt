package com.miempresa.cot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_newtempactivity.*
import kotlinx.android.synthetic.main.activity_temperactivity.*

class newtempactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newtempactivity)

        setSupportActionBar(findViewById(R.id.mitoolbar))
        //supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_preferences)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnEnviar.setOnClickListener(){
            envio()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()

        if (id == android.R.id.home){
            startActivity(Intent(this,temperactivity::class.java))
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun envio(){
        var inicio = txttempinicial.text.toString()
        var final = txttempfinal.text.toString()

        var intent = Intent(this, temperactivity::class.java)
        intent.putExtra("inicio",inicio)
        intent.putExtra("final",final)
        startActivity(intent)
        finish()
    }
}