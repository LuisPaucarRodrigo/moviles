package com.miempresa.cot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_conversor.*
import kotlinx.android.synthetic.main.fragment_conversor.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [conversorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class conversorFragment : Fragment() {
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
        val view:View = inflater.inflate(R.layout.fragment_conversor, container, false)
        var cmbgrados = view.findViewById<Spinner>(R.id.cmbgrados)
        val categorias = arrayOf("Fahrenheit","Kelvin")
        cmbgrados.adapter = ArrayAdapter(
            view.context,android.R.layout.simple_spinner_item,categorias
        )

        view.btnconvertir.setOnClickListener(){
            var txtvalor = view.findViewById<EditText>(R.id.txtvalor).getText().toString()
            var txtrespuesta = view.findViewById<TextView>(R.id.respuesta)
            if (txtvalor != null ) {
                val categoria = cmbgrados.selectedItem.toString()
                when (categoria) {
                    "Fahrenheit" -> txtrespuesta.text = Fahrenheit(txtvalor.toDouble())
                    "Kelvin" -> txtrespuesta.text = Kelvin(txtvalor.toDouble())
                }
            }else{
                Toast.makeText(
                    context,
                    "Complete el campo valor",
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
         * @return A new instance of fragment conversorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            conversorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    }

    private fun Fahrenheit(valor:Double):String {
        val df = DecimalFormat("#.##")
        return df.format((valor-32)*5/9)
    }
    private fun Kelvin(valor:Double):String {
        val df = DecimalFormat("#.##")
        return df.format(valor -273.15)
    }
