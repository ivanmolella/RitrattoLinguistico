package com.anastaasiasenyshyn.ritrattolinguistico.test

import android.util.Log

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

    //determinareNumMaxeNumMinConForEachProva()


    //determinareNumMaxeNumMin()

    //printAsciTable()
    //min=0
    //val array = mutableListOf(4,23,15,21,3,80,1800)

    //trovaMax(array)
    //trovaMin(array)

    //esercizioTreVettoriconArrayCaricato()
    //esercizioTreVettoriconArraydaCaricaredaTastiera()

    //esercizioFiveVettori()

    //esercizioDieciVettori()

    //esercizioDieciVettoridaCaricaredaTasiera()

    //esercizioDodiciVettoriCaricati()

    //esercizioDodiciVettoriDaInserire()

    //esercizio15()

    //esercizio25()
    //esercizio25Ivan()

    //esercizio28()

    //newesercizio()

    esercizio36()



}



fun esercizio36() {
    /*Dato un vettore numerico di N posizioni calcolare la media aritmetica delle componenti in
            posizione dispari e la media aritmetica di quelle in posizione pari. Determinare quale è la
            media maggiore.*/
    var arrayMutable : MutableList<Int> = mutableListOf(20,32,45,80,800,25)
    var arrayPari : MutableList<Int> = mutableListOf()
    var arrayDispari : MutableList<Int> = mutableListOf()
    var sommaElementi = 0
    var sommaElementi2 = 0

    println("numeri inseriti: $arrayMutable")

    arrayMutable.forEachIndexed { index, value ->
        if (index % 2 ==0 ){
            arrayPari.add(value)
        }
        else {
            arrayDispari.add(value)
        }
    }
    println("array pari : $arrayPari")
    println("array Dispari : $arrayDispari")

    arrayPari.forEachIndexed{ index, value ->
        sommaElementi = sommaElementi + value
    }
    println("somma elementi array pari : $sommaElementi")

    arrayDispari.forEachIndexed{index,value ->
        sommaElementi2 = sommaElementi2 + arrayDispari[index]
    }
    println("somma elementi array dispari : $sommaElementi2")

}

fun newesercizio() {
    /*Dato un vettore numerico di N posizioni calcolare la media aritmetica delle componenti*/
    //val list : List<Int> = listOf(20,32,45,80,800,25)
    val list : List<Int> = listOf(10,20,40,30)
    var sommaElementi = 0
    var numeroElementi = list.size

    println("elementi inseriti: $list")

    for (i in 0..list.size -1){
        sommaElementi = sommaElementi + list[i]
    }

    val mediaAritmetica = sommaElementi.toDouble() / numeroElementi.toDouble()
    println("Somma Elementi: $sommaElementi")
    println("Numero Elementi: $numeroElementi")
    println ("Media: $mediaAritmetica")







}


fun esercizio28() {
    val arrayMutable : MutableList<Int> = mutableListOf(20,32,45,80,800,25)
    arrayMutable.add(500)
    arrayMutable.add(1500)
    arrayMutable.add(2500)

    val arrayPari : MutableList<Int> = mutableListOf()
    val arrayDispari : MutableList<Int> = mutableListOf()

    arrayMutable.forEachIndexed{ index, value ->
        //println("Elemento: $value at index ($index)")
        if (index % 2 == 0){
            arrayPari.add(value)
        }else {
            arrayDispari.add(value)
        }
    }
    arrayPari.forEach { value ->
        println("Valore a posizioni pari: $value")
    }

    arrayDispari.forEach { value ->
        println("Valore a posizioni dispari: $value")
    }





}

fun esercizio25() {
    /*In un vettore alfanumerico trovare la stringa (o le stringhe) di lunghezza maggiore,
precisandone la posizione occupata all'interno del vettore.*/
    val vettorealfanumerico : List<String> = listOf("pippo","3daysgrays", "Sole24ore", "Nom25")
    var max = 0
    var p = 0
    vettorealfanumerico.forEach{ item->
        println("l'elenco è $item")
        for (j in 0..item.length){
            if (max < item.length){
                max = item.length
                p++
            }
        }

    }
    println("il valore max è $max")
    println("la posizione è $p")
}

fun esercizio25Ivan() {
    /*In un vettore alfanumerico trovare la stringa (o le stringhe) di lunghezza maggiore,
precisandone la posizione occupata all'interno del vettore.*/
    val vettorealfanumerico : List<String> = listOf("pippo","3daysgrays", "Sole24ore", "Nom25")
    var max = 0
    var p = 0
    var maxWord : String? = null
    vettorealfanumerico.forEachIndexed{index, item->
        println("l'elenco è $item")
        if (max < item.length){
            max = item.length
            p = index
            maxWord=item
        }
    }
    println("il valore max è $max")
    println("la posizione è $p")
    println("la parola è $maxWord")
}

fun esercizio15() {

    val elencoNome : List<String> = listOf("Pera","Mela","Automobile","Borraccia")

    println("Inserisci la parola in input: \n")
    val input = readLine()!!
    var exist = false
    elencoNome.forEach { nome ->
        if (nome.equals(input,true)){
            println("Il nome $nome è presente nell'elenco")
            exist=true
        }
    }

    if (exist == false){
        println("Il nome $input non è presente nell'elenco")
    }

}

fun esercizioDodiciVettoriDaInserire() {
    println("Inserisci dei numeri: \n")
    val listaStringhe : List<String> = readLine()!!.split(' ')
    val listaInt : List<Int> = listStringToListInteger(listaStringhe)
    println("I numeri inseriti sono: $listaInt")
    val array = listaInt
    var posizioneNumDispari = 0
    var numeroDispariIncontrato = 0

    for (i in 0..array.size - 1){
        if (array[i] % 2 !=0 ){
            posizioneNumDispari = i
            numeroDispariIncontrato = array[i]
            break
        }
        if (array[i] != 0){
            println("I numeri considerati sono: ${array[i]}")
        }

    }
    println("Il numero dispari incontato: $numeroDispariIncontrato")
    println("Posizione numero dispari: $posizioneNumDispari")



}


fun esercizioDodiciVettoriCaricati() {
    val array = mutableListOf(4,0,23,15,1800,21,3,80)
    println("I numeri inseriti sono: $array")
    var posizioneNumDispari = 0
    var numeroDispariIncontrato = 0

    for (i in 0..array.size - 1){
        if (array[i] % 2 !=0 ){
            posizioneNumDispari = i
            numeroDispariIncontrato = array[i]
            break
        }
        if (array[i] != 0){
            println("I numeri considerati sono: ${array[i]}")
        }

    }
    println("Posizione numero dispari: $posizioneNumDispari")
    println("Il numero dispari incontato: $numeroDispariIncontrato")




}

fun esercizioDieciVettoridaCaricaredaTasiera() {
    println("Inserisci dei numeri: \n")
    val listaStringhe : List<String> = readLine()!!.split(' ')
    val listaInt : List<Int> = listStringToListInteger(listaStringhe)
    println("I numeri inseriti sono: $listaInt")
    val array = listaInt
    var max = 0
    var max2 = 0
    var p = 0
    var p2 = 0
    for(i in 0..array.size - 1){
        val valorecorrente = array[i]
        if(max < valorecorrente){
            max2 = max
            p2 = p
            max = valorecorrente
            p = i
        } else if (valorecorrente>max2){
            max2=valorecorrente
            p2=i

        }

    }
    println("Il valore massimo è $max")
    println("Il secondo massimo è $max2 nella posizione $p2")
}

fun esercizioDieciVettori() {
    val array = mutableListOf(4,23,15,1800,21,3,80)
    var max = 0
    var max2 = 0
    var p = 0
    var p2 = 0
    for(i in 0..array.size - 1){
        val valorecorrente = array[i]
        if(max < valorecorrente){
            max2 = max
            p2 = p
            max = valorecorrente
            p = i
        }
        else if (valorecorrente>max2){
            max2=valorecorrente
            p2=i

        }

    }
    println("Il valore massimo è $max")
    println("Il secondo massimo è $max2 nella posizione di $p2")
}

fun esercizioFiveVettori() {
    println("Inserisci dei numeri: \n")
    val listaStringhe : List<String> = readLine()!!.split(' ')
    val listaInt : List<Int> = listStringToListInteger(listaStringhe)
    println("I numeri inseriti sono: $listaInt")
    val array = listaInt
    var somma = 0
    var numeroElementi=0

    for (i in 0..array.size -1){
        //val numerocorrente = array[i]
        if (array[i] == 0){
            break
       }
        println("i numeri considerati ${array[i]}")


        somma = somma + array[i]
        numeroElementi++ //numeroElementi = numeroElementi + 1

    }
   println("somma numeri inseriti fino a 0 è $somma ")
   println("Il numero di elementi considerati è $numeroElementi ")

}

fun esercizioTreVettoriconArraydaCaricaredaTastiera() {
    println("Inserisci dei numeri: \n")
    val listaStringhe : List<String> = readLine()!!.split(' ')
    val listaInt : List<Int> = listStringToListInteger(listaStringhe)
    println("I numeri inseriti sono: $listaInt")
    val array = listaInt
    var somma = 0
    for (i in 0..array.size -1){
        val numerocorrente = array[i]
        somma = somma + numerocorrente
    }
    println("La somma degli elementi sopra riportati è $somma")

}

fun esercizioTreVettoriconArrayCaricato() {
    val array = mutableListOf(4,23,15,21,3,80,1800)
    var somma = 0
    for (i in 0..array.size -1) {
        val valorecorrente = array[i]
        somma= somma+valorecorrente
    }
    println("La somma degli elementi è $somma")
}

fun printAsciTable() {

    for (i in 0 .. 255){
        println("Asci Character: ${i.toChar()} - Asci Code: $i")
    }
}


fun trovaMax(array: List<Int>){
    var max = 0
    for(i in 0..array.size - 1){
        val valorecorrente = array[i]
        if(max < valorecorrente){
            max = valorecorrente
        }

    }
    println("Il valore massimo è $max")
}

fun trovaMin(array: List<Int>){
    var min = 0
    for(i in 0..array.size -1){
        val valorecorrente = array[i]
        if (i == 0){
            min = valorecorrente
        }
        if(min > valorecorrente){
            min = valorecorrente
        }
    }
    println("Il valore minimo è $min")
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
    val listaStr : List<String> = readLine()!!.split(' ')
    val listaInt : List<Int> = listStringToListInteger(listaStr)
    println("I numeri inseriti sono: $listaInt")
    val newlista = listaInt.max().toDouble()
    println("Il numero massimo è : $newlista")
    val thenewestlista = listaInt.min().toDouble()
    println("Il numero minimo è : $thenewestlista")

}

fun listStringToListInteger(lista: List<String>): List<Int> {
    val listaInt : MutableList<Int> = mutableListOf()
    lista.forEach { valoreStr ->
        listaInt.add(valoreStr.toInt())
    }
    return listaInt
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