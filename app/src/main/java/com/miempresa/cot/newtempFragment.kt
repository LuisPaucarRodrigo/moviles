package com.miempresa.cot

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_newtempactivity.*
import kotlinx.android.synthetic.main.activity_newtempactivity.view.*
import kotlinx.android.synthetic.main.fragment_newtemp.view.*
import kotlinx.android.synthetic.main.fragment_newtemp.view.btnEnviar
import org.json.JSONException
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [newtempFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class newtempFragment : Fragment() {
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
        val view:View =  inflater.inflate(R.layout.fragment_newtemp, container, false)
        val policy= StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        view.btnEnviar.setOnClickListener(){
            var tempinico = view.findViewById<EditText>(R.id.txttempinicial).getText()
            var tempfinal = view.findViewById<EditText>(R.id.txttempfinal).getText()
            if (tempinico != null && tempfinal != null ){
                val queue = Volley.newRequestQueue(context)
                val json = JSONObject()
                var url= getString(R.string.urlAPI)+"/api/temperatura/1"
                json.put("temp_ini",tempinico.toString())
                json.put("temp_fin",tempfinal.toString())
                json.put("almacen_id","1")
                val jsonArray = JsonObjectRequest(
                    Request.Method.PUT,url,json,
                    Response.Listener{ response ->
                        try{
                            val transaccion: FragmentTransaction =requireActivity().supportFragmentManager.beginTransaction()
                            transaccion.replace(R.id.contenedor, temperFragment())
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
        view.btnvolver.setOnClickListener(){
            val transaccion: FragmentTransaction =requireActivity().supportFragmentManager.beginTransaction()
            transaccion.replace(R.id.contenedor, temperFragment())
            transaccion.commit()
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
         * @return A new instance of fragment newtempFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            newtempFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}