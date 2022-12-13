package com.miempresa.cot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_newaccount.*
import kotlinx.android.synthetic.main.activity_temperactivity.*

class temperactivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private var datos:String = ""
    private var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperactivity)

        val navigationView:NavigationView=findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        setSupportActionBar(findViewById(R.id.mitoolbar))
        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val temperFragment = temperFragment()
        val transaccion: FragmentTransaction =supportFragmentManager.beginTransaction()
        transaccion.replace(R.id.contenedor,temperFragment)
        transaccion.commit()

        //btnAgregar.setOnClickListener(){
        //    startActivity(Intent(this,newtempactivity::class.java))
       //     finish()
        //}

        var bundle: Bundle? = intent.extras
        if(bundle !=null){
            id = bundle.getString("id").toString()

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id: Int = item.getItemId()
        if (id == android.R.id.home) {
            layout_lateral.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_temperatura-> temperatura()
            R.id.nav_cuenta-> dateperson()
            R.id.nav_conversor-> conversor()
            R.id.nav_historial-> Toast.makeText(this,"Elegiste Historial", Toast.LENGTH_LONG).show()
            R.id.nav_notificaciones-> Toast.makeText(this,"Elegiste Notificaciones", Toast.LENGTH_LONG).show()
            R.id.nav_contactos-> cerrarsesion()
        }
        layout_lateral.closeDrawer(GravityCompat.START)
        return true
    }

    fun dateperson(){
        val persondateFragment = persondateFragment()
        val args =Bundle()
        args.putString("id",id)
        persondateFragment.arguments = args
        val transaccion: FragmentTransaction =supportFragmentManager.beginTransaction()
        transaccion.replace(R.id.contenedor,persondateFragment)
        transaccion.commit()
    }

    fun temperatura(){
        val transaccion: FragmentTransaction =supportFragmentManager.beginTransaction()
        transaccion.replace(R.id.contenedor,temperFragment())
        transaccion.commit()
    }

    fun conversor(){
        val transaccion: FragmentTransaction =supportFragmentManager.beginTransaction()
        transaccion.replace(R.id.contenedor,conversorFragment())
        transaccion.commit()
    }

    fun cerrarsesion(){
        startActivity(Intent(this,authactivity::class.java))
        this.finish()
    }
}