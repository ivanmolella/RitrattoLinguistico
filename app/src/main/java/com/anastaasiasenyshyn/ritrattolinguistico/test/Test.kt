package com.anastaasiasenyshyn.ritrattolinguistico.test

fun main() {
    //readLine()

    readNumbersFromCommandLine()
    //readStringFromCommandLine()

}

fun readNumbersFromCommandLine(){
    println("Insert a string:\n")
    val numbers= readLine()!!.split(' ')
    numbers.forEach {
        println("Inserita numero: $it")
    }
}

fun readStringFromCommandLine(){
    println("Insert some numbers separated by space:\n")
    val string = readLine()!!
    println("Inserito string: $string")
}