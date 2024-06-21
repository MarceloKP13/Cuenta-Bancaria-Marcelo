package com.example.banco

class CuentaBancaria(
    val numeroCuenta: String,
    val titular: String,
    var saldo: Double
) {


    fun depositar(monto: Double) {
        saldo += monto
        println("Depósito realizado. Nuevo saldo: $saldo")
    }


    fun retirar(monto: Double): Boolean {
        if (monto <= saldo) {
            saldo -= monto
            println("Retiro realizado. Nuevo saldo: $saldo")
            return true
        } else {
            println("Saldo insuficiente para realizar el retiro.")
            return false
        }
    }

    fun consultarSaldo(): Double {
        return saldo
    }
}