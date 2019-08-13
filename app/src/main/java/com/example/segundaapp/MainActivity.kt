package com.example.segundaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cargarBoton()
        cargarSpinner()
        validarCheckBox()
    }
    fun validarCheckBox(){
        cbPosgrado.setOnCheckedChangeListener {buttonView, isChecked ->
            cbLic.isChecked = true
        }
        cbLic.setOnCheckedChangeListener{buttonView, isChecked ->
            if(cbPosgrado.isChecked)
                cbLic.isChecked = true
        }
    }
    fun cargarSpinner(){
        val carreraEstudiar = arrayOf("Software","Biomedica","Electronica","Arquitectura","Derecho")
        val adapter : ArrayAdapter<String> = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            carreraEstudiar // Array
        )
        spCarrera.adapter = adapter
    }
    fun cargarBoton(){
        btnValidar.setOnClickListener{
            var cadenaTexto : String
            var licenciatura : String
            var posgrado : String = ""
            var carrera : String
            if(validar() == false){
                if(cbPosgrado.isChecked){
                    posgrado = " tiene un Posgrado"
                }
                if(cbLic.isChecked && cbPosgrado.isChecked){
                    licenciatura = " y usted es un Licenciado en"
                }else{
                    licenciatura = " y usted esta estudiando "
                }

                carrera = spCarrera.getSelectedItem().toString()
                cadenaTexto = "Tu mail es ${txtMail.text} $posgrado $licenciatura $carrera "
                txtRespuesta.text = cadenaTexto
            }
        }
    }
     fun validar() : Boolean{
        var validadorInterno = false
        if(TextUtils.isEmpty(txtMail.text.toString()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(txtMail.text.toString()).matches()){
            txtMail.error = "Capture un email correcto"
            validadorInterno = true
        }
        if (TextUtils.isEmpty(txtEdad.text.toString())){
            txtEdad.error = "Capture una edad valida"
            validadorInterno = true
        }
         if(!cbLic.isChecked){
             validadorInterno = true
             Toast.makeText(getApplicationContext(), "Porfavor verifique su licenciatura o posgrado", Toast.LENGTH_SHORT).show()
         }
        return validadorInterno
    }

}
