package com.example.banco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var cuentaBancaria: CuentaBancaria
    private lateinit var textViewValor: TextView
    private lateinit var editTextDepositar: EditText
    private lateinit var editTextRetirar: EditText
    private lateinit var buttonMostrarSaldo: Button

    private var saldoVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cuentaBancaria = CuentaBancaria("1750748327", "Marcelo Torres", 2000.0)

        textViewValor = findViewById(R.id.textViewValor)
        editTextDepositar = findViewById(R.id.editTextDepositar)
        editTextRetirar = findViewById(R.id.editTextRetirar)
        buttonMostrarSaldo = findViewById(R.id.buttonMostrarSaldo)
        val buttonDepositar = findViewById<Button>(R.id.buttonDepositar)
        val buttonRetirar = findViewById<Button>(R.id.buttonRetirar)

        actualizarSaldo()

        buttonMostrarSaldo.setOnClickListener {
            saldoVisible = !saldoVisible
            actualizarSaldo()
        }

        buttonDepositar.setOnClickListener {
            val monto = editTextDepositar.text.toString().toDoubleOrNull() ?: 0.0
            cuentaBancaria.depositar(monto)
            editTextDepositar.text.clear()
            actualizarSaldo()
        }

        buttonRetirar.setOnClickListener {
            val monto = editTextRetirar.text.toString().toDoubleOrNull() ?: 0.0
            cuentaBancaria.retirar(monto)
            editTextRetirar.text.clear()
            actualizarSaldo()
        }
    }

    private fun actualizarSaldo() {
        val saldoActual = cuentaBancaria.consultarSaldo()

        if (saldoVisible) {
            textViewValor.text = "$${String.format("%.2f", saldoActual)}"
        } else {
            val saldoEncubierto = "***${String.format("%.2f", saldoActual).takeLast(3)}"
            textViewValor.text = saldoEncubierto.replace(Regex("\\d"), "*")
        }
    }
}

