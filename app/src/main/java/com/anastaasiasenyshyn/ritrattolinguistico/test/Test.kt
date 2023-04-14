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

    //calcolaSommaNumeriConArrayIndefinito()

    //calcoladoppioNumero()

    //calcolailquadratodellasommanumeri()

    //calcolaQuadratodellaSommaNumeriIndefiniti()

    //numeriOrdineCrescente()

    //numeriOrdineDecrescenteProva()

    //determinareNumMaxeNumMin()

    //determinareNumMaxeNumMinConForEachProva()

    //esercizioDiciotto()



}

fun esercizioDiciotto() {
    val dollaro : Double = 1.75
    val sterlina : Double = 2.80
    val valoredollaro : Double = dollaro * 1.514
    val valoresterlina : Double = sterlina * 2.52
    val totaleeuro = valoredollaro + valoresterlina
    println("il valore euro che ha il sigonre è : $totaleeuro")



}




fun determinareNumMaxeNumMinConForEachProva() {
    println("Inserisci dei numeri: \n")
    val lista : List<String> = readLine()!!.split(' ')
    println("I numeri inseriti sono: $lista")
    lista.forEach {numeri->
        val newlista = lista.min().toDouble()
        println("Il numero minimo is: $newlista")
        val thenewestlista = lista.max().toDouble()
        println("Il numero massimo is: $thenewestlista")
    }
}

fun determinareNumMaxeNumMin() {
    println("Inserisci dei numeri: \n")
    val lista : List<String> = readLine()!!.split(' ')
    println("I numeri inseriti sono: $lista")
    val newlista = lista.max().toDouble()
    println("Il numero massimo è : $newlista")
    val thenewestlista = lista.min().toDouble()
    println("Il numero minimo è : $thenewestlista")

}

fun numeriOrdineDecrescenteProva() {
    println("Inserisci dei numeri:\n")
    val lista : List<String> = readLine()!!.split(' ')
    println("numeri inseriti: $lista")
    val newlista = lista.sortedByDescending { it.toDouble() }
    println("numeri in descending order are: $newlista")

}

fun numeriOrdineCrescente() {
    
    println("Inserisci dei numeri: \n")
    val lista : List<String> = readLine()!!.split(' ')
    var min : Int? = null
    var max : Int? = null

    if (lista[0].toInt() > lista[1].toInt()){
        max = lista[0].toInt()
        min = lista[1].toInt()
    }else{
        max = lista[1].toInt()
        min = lista[0].toInt()
    }
    println("${min} ${max}")


}

fun calcolaQuadratodellaSommaNumeriIndefiniti(){
    println("Inserisci dei numeri reali: \n")
    val lista : List<String> = readLine()!!.split(' ')
    var somma = 0.0
    lista.forEach {
        somma = somma + it.toDouble()
    }
    var doppiosomma = somma * 2
    println("il doppio della somma dei numeri è $doppiosomma")
}

fun calcolailquadratodellasommanumeri() {
    println("inserisci tre numeri reali: \n")
    val (a,b,c) = readLine()!!.split(' ')
    val somma = a.toDouble() + b.toDouble() + c.toDouble()
    val doppiosomma= somma * 2
    println("il doppio della somma dei numeri è $doppiosomma")
}

fun calcoladoppioNumero() {
    println("Inserisci un numero")
    val numero = readLine()!!
    val doppio = numero.toDouble() * 2
    println("il doppio di $numero è $doppio")
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