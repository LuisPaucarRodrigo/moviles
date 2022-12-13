package com.miempresa.cot

import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_auth.view.*
import kotlinx.android.synthetic.main.activity_newaccount.*
import kotlinx.android.synthetic.main.fragment_persondate.*
import kotlinx.android.synthetic.main.fragment_persondate.view.*
import kotlinx.android.synthetic.main.fragment_temper.view.*
import org.json.JSONException
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [persondateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class persondateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_persondate, container, false)
        //val policy= StrictMode.ThreadPolicy.Builder().permitAll().build()
        //StrictMode.setThreadPolicy(policy)
        val queue = Volley.newRequestQueue(context)

        if (arguments != null){
            id = requireArguments().getString("id").toString()
        }

        var urltemp = getString(R.string.urlAPI)+"/api/usuario2/" + id
        val stringRequesttemp = JsonObjectRequest(urltemp,
            Response.Listener { response ->
                try {
                    val valor = response
                    if (valor != null){
                        view.txtusuariodate.setText(valor.getString("nombre"))
                        view.txtapellidodate.setText(valor.getString("apellidos"))
                        view.txtdirecciondate.setText(valor.getString("direccion"))
                        view.txtemaildate.setText(valor.getString("email"))
                        view.txtestadodate.setText(valor.getString("estado"))
                        view.txttelefonodate.setText(valor.getString("sexo"))
                    }

                } catch (e: JSONException) {
                    Toast.makeText(
                        context,
                        "Usuario y Password incorrectos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }, Response.ErrorListener {
                Toast.makeText(
                    context,
                    "Compruebe que tiene acceso a internet",
                    Toast.LENGTH_LONG
                ).show()
            })
        queue.add(stringRequesttemp)

        view.btneditar.setOnClickListener(){
            var txtusuariodate = view.findViewById<EditText>(R.id.txtusuariodate).getText()
            var txtapellidodate = view.findViewById<EditText>(R.id.txtapellidodate).getText()
            var txtdirecciondate = view.findViewById<EditText>(R.id.txtdirecciondate).getText()
            var txtemaildate = view.findViewById<EditText>(R.id.txtemaildate).getText()
            var txtestadodate = view.findViewById<EditText>(R.id.txtestadodate).getText()
            var txttelefonodate = view.findViewById<EditText>(R.id.txttelefonodate).getText()
            if (txtusuariodate != null && txtapellidodate!= null && txtdirecciondate!= null &&
                txtemaildate!= null && txtestadodate!= null && txttelefonodate!= null){
                val queue = Volley.newRequestQueue(context)
                val json = JSONObject()
                var url= getString(R.string.urlAPI)+"/api/usuario2/"+id
                json.put("nombre",txtusuariodate.toString())
                json.put("apellidos",txtapellidodate.toString())
                json.put("email",txtemaildate.toString())
                json.put("estado",txtestadodate.toString())
                json.put("direccion",txtdirecciondate.toString())
                json.put("telefono",txttelefonodate.toString())
                val jsonArray = JsonObjectRequest(
                    Request.Method.PUT,url,json,
                    Response.Listener{ response ->
                        try{
                            val transaccion: FragmentTransaction =requireActivity().supportFragmentManager.beginTransaction()
                            transaccion.replace(R.id.contenedor, persondateFragment())
                            transaccion.commit()
                        }catch (e: JSONException){
                            Toast.makeText(
                                context,"Intentelo mas Tarde",
                                Toast.LENGTH_LONG).show()
                        }
                    },
                    Response.ErrorListener{
                        Toast.makeText(context,"Comprueba que tiene acceso a internet: ",
                            Toast.LENGTH_LONG).show()
                    })
                queue.add(jsonArray)
            }else{
                Toast.makeText(
                    context,
                    "Complete los campos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment persondateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            persondateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}