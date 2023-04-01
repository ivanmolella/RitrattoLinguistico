package com.anastaasiasenyshyn.ritrattolinguistico.test

fun main() {

//    val temp1: Int = 10
//    val temp2: Int = 15
//    val temp3: Int = 30

//    var temperature : List<Double> = listOf(2.0,18.0,12.0) //(0... n -1)
//    var sommaTemperature = temperature[0] + temperature[1] + temperature[2]
//    var media = (sommaTemperature / 3.0)
//
//    println("La tempreatura media della giornata è: $media")
    //readLine()

    //readNumbersFromCommandLine()
    //readStringFromCommandLine()
    //calcolaMediaTemperature()

    //calcolaSommaNumeriSenzaArray()

    //calcolaSommaNumeriConArray()

    calcolaSommaNumeriConArrayIndefinito()
}

fun calcolaSommaNumeriConArrayIndefinito() {
    println("Inserisci i numeri della lista:\n")
    val lista : List<String> = readLine()!!.split(' ')
    var somma = 0.0
    lista.forEach { numero ->
        somma = somma + numero.toDouble()
    }
    println("La somma degli N numeri è: $somma")

}

fun calcolaSommaNumeriConArray() {
    println("Inserisci i 2 numeri:\n")
    val (a,b,c) = readLine()!!.split(' ')

    val somma = a.toDouble() + b.toDouble()

    println("La somma dei 2 numeri è: $somma")
}

fun calcolaSommaNumeriSenzaArray() {
    println("Inserisci il primo numero:\n")
    val numero1 = readLine()!!
    println("Il primo numero inserito è: $numero1")

    println("Inserisci il secondo numero:\n")
    val numero2 = readLine()!!
    println("Il secondo numero inserito è: $numero2")

    val somma = numero1.toDouble() + numero2.toDouble()

    println("La somma dei 2 numeri è: $somma")
}

fun calcolaMediaTemperature() {
    println("Inserisci le temperature:\n")
    val temperature : List<String> = readLine()!!.split(' ')
    val totaleTemperature = calcolaTotaleTemperature(temperature)
    val media = totaleTemperature / temperature.size
    println("La media delle temperature è: $media")
}

fun calcolaTotaleTemperature(temperature: List<String>): Double {
    var totaleTemperatura : Double = 0.0
    temperature.forEach { temperatura ->
        totaleTemperatura = totaleTemperatura + temperatura.toDouble()
    }

    return totaleTemperatura
}

fun readNumbersFromCommandLine(){
    println("Insert some number:\n")
    val numbers= readLine()!!.split(' ')
    numbers.forEach {
        println("Inserita numero: $it")
    }
}

fun readStringFromCommandLine(){
    println("Insert a string:\n")
    val string = readLine()!!
    println("Inserita string: $string")
}