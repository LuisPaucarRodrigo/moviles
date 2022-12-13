package com.miempresa.cot

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_temper.*
import kotlinx.android.synthetic.main.fragment_temper.view.*
import org.json.JSONException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [temperFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class temperFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view:View =  inflater.inflate(R.layout.fragment_temper, container, false)

        val queue = Volley.newRequestQueue(context)
        view.btnAgregar.setOnClickListener(){
            val transaccion: FragmentTransaction =requireActivity().supportFragmentManager.beginTransaction()
            transaccion.replace(R.id.contenedor,newtempFragment())
            transaccion.commit()
        }

        view.txttemperactual.setOnClickListener(){
            var urltemp = getString(R.string.urlAPI)+"/api/almacen/1"
            val stringRequesttemp = JsonObjectRequest(urltemp,
                Response.Listener { response ->
                    try {
                        val valor = response
                        if (valor != null){
                            view.txttemperactual.text= valor.getString("temperatura")+"°"
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
        }

        //while (true){
        var urltemp = getString(R.string.urlAPI)+"/api/almacen/1"
        val stringRequesttemp = JsonObjectRequest(urltemp,
            Response.Listener { response ->
                try {
                    val valor = response
                    if (valor != null){
                        view.txttemperactual.text= valor.getString("temperatura")+"°"
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
       // }

        var url = getString(R.string.urlAPI)+"/api/temperatura/1"
        val stringRequest = JsonObjectRequest(url,
            Response.Listener { response ->
                try {
                    val valor = response
                    if (valor != null){
                        view.txtinicio.text= valor.getString("temp_ini")+"°"
                        view.txtfinal.text= valor.getString("temp_fin")+"°"
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
        queue.add(stringRequest)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment temperFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            temperFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}