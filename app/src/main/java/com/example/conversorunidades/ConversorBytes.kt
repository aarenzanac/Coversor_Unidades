package com.example.conversorunidades

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.conversor_bytes.*
import kotlin.math.pow

class ConversorBytes : AppCompatActivity() {

    var cantidad: Double = 0.00
    var origen: Int = -1
    var unidadOrigen: String = ""
    var destino: Int = -1
    var unidadDestino: String = ""
    var resultado: Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversor_bytes)
        setTitle(R.string.app_bytes)

        buttonConvertir.setOnClickListener {
            cantidad = obtenerCantidad()
            if (cantidad != 0.00){
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
        if(textInputCantidad.text.isNullOrBlank() || textInputCantidad.text.toString().toDouble() == 0.00 ){
            textViewResultado.setText(R.string.errorCantidad)
            return 0.00
        }else{
            val input: Double = textInputCantidad.text.toString().toDouble()
            println("**************La cantidad introducida es: ${input} *********************")
            return input
        }
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
        }else if(origen > destino ){
            if(origen == 2 && destino == 1){
                resultado = cantidad * 8
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }else if(origen > 2 && destino == 1){
                resultado = (cantidad * 8) *(1024.00.pow((origen-1)-destino))
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }else {
                resultado = cantidad*(1024.00.pow(origen - destino))
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }

        }else{
            if(origen == 1 && destino == 2){
                resultado = cantidad / 8
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }else if(origen == 1 && destino > 2){
                var operacion: Double = 0.00        //Variables para calcular todas las divisiones. Donde se almacena el resultado de dividir cada iteracion entre 1024
                var cantidadDivision: Double = cantidad ////Variables para calcular todas las divisiones. Temporal para el bucle for
                for(num in origen..destino-2) {
                    operacion = cantidadDivision / 1024.00
                    cantidadDivision = operacion
                }
                resultado = cantidadDivision / 8
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }else{
                var operacion: Double = 0.00        //Variables para calcular todas las divisiones. Donde se almacena el resultado de dividir cada iteracion entre 1024
                var cantidadDivision: Double = cantidad ////Variables para calcular todas las divisiones. Temporal para el bucle for
                for(num in origen..destino-1) {
                    operacion = cantidadDivision / 1024.00
                    cantidadDivision = operacion
                }
                resultado = cantidadDivision
                textViewResultado.text = (cantidad.toString() + " " + unidadOrigen + " = " + resultado + " " + unidadDestino)
                resultado = 0.00
            }

        }
    }
}