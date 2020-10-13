package com.example.conversorunidades

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.radio_group_unidades.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAcceder.setOnClickListener {
            if(radioButtonBytes.isChecked){
                val intentBytes = Intent(this, ConversorBytes::class.java)
                startActivity(intentBytes)
            }else if(radioButtonMetros.isChecked){
                val intentMetros = Intent(this, ConversorMetros::class.java)
                startActivity(intentMetros)

            }else if(radioButtonGramos.isChecked){
                val intentGramos = Intent(this, ConversorGramos::class.java)
                startActivity(intentGramos)
            }else if(radioButtonLitros.isChecked) {
                val intentLitros = Intent(this, ConversorLitros::class.java)
                startActivity(intentLitros)
            }else{
                val intentGrados = Intent(this, ConversorTemperatura::class.java)
                startActivity(intentGrados)
            }
        }
    }


}