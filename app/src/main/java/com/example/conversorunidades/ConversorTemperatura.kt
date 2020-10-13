package com.example.conversorunidades

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.conversor_bytes.*

class ConversorTemperatura : AppCompatActivity() {

    var cantidad: Double = 0.00
    var origen: Int = -1
    var unidadOrigen: String = ""
    var destino: Int = -1
    var unidadDestino: String = ""
    var resultado: Double = 0.00


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversor_temperatura)
        setTitle(R.string.app_grados)

        buttonConvertir.setOnClickListener {
            cantidad = obtenerCantidad()
            if (cantidad != null){
                obtenerUnidadOrigen()
                if(origen < 1) {
                    textViewResultado.setText(R.string.errorOrigen)
                }else{
                    textViewResultado.text = ""
                    obtenerUnidadDestino()
                    if(destino < 1){
                        textViewResultado.setText(R.string.errorDestino)
                    }else{
                        textViewResultado.text = ""
                        calcular(cantidad, origen, destino)
                    }
                }
            }
        }

        buttonVolver.setOnClickListener {
            finish()
        }
    }

    private fun obtenerCantidad(): Double{

        val input: Double = textInputCantidad.text.toString().toDouble()
        println("**************La cantidad introducida es: ${input} *********************")
        return input

    }

    private fun obtenerUnidadOrigen(){
        origen = spinnerUnidadesOrigen.selectedItemPosition
        unidadOrigen = spinnerUnidadesOrigen.getItemAtPosition(origen).toString()
    }

    private fun obtenerUnidadDestino(){
        destino = spinnerUnidadesDestino.selectedItemPosition
        unidadDestino = spinnerUnidadesDestino.getItemAtPosition(destino).toString()
    }

    private fun calcular(cantidad: Double, origen: Int, destino: Int){

        if(origen == destino){
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " +cantidad.toString() + " " + unidadDestino)
        }else if(origen < destino ){
            resultado = ((cantidad * 9) /5) + 32.00
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }else{
            resultado = (((cantidad - 32) * 5) /9)
            resultado = String.format("%.3f", resultado).toDouble()
            textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
            resultado = 0.00
        }
    }

}