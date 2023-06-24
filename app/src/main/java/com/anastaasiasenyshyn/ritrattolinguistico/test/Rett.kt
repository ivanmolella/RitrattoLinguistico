package com.anastaasiasenyshyn.ritrattolinguistico.test

/*Scrivere una classe Rettangolo i cui oggetti rappresentano rettangoli. Lo stato interno di
un rettangolo e' dato dai valori della base e dell’altezza. Un rettangolo deve mettere a disposizione tre
operazioni: ridimensiona() che prende come parametri due nuovi valori di base e altezza e aggiorna
lo stato, perimetro() che restituisce il perimetro e area() che restituisce l’area. Prevedere inoltre un
costruttore che inizializza base e altezza del rettangolo.
Per testare la classe, scrivere un programma TestRettangolo che crea tre rettangoli (oggetti della
classe Rettangolo) ne calcola la somma delle aree e la somma dei perimetri (stampando i risultati),
ridimensiona uno dei tre rettangoli e ristampa le somme.*/

interface ShapeInterface {
    fun ridimensiona(newBase: Double, newAltezza: Double)
    fun perimetro(): Double

    fun area() : Double
}

fun printShapeInfo(shape : ShapeInterface){
    println("Area: ${shape.area()} perimetro: ${shape.perimetro()}")
}

fun printRectInfo(rect : Rettangolo){
    println("Area: ${rect.area()} perimetro: ${rect.perimetro()} diagonale: ${rect.calcolaDiagonale()}")
}

open class Rettangolo(base: Double, altezza: Double) : ShapeInterface {
    var base: Double? = null
    var altezza: Double? = null

    init {
        this.base = base
        this.altezza = altezza
    }

    override fun ridimensiona(newBase: Double, newAltezza: Double){
        this.base = newBase
        this.altezza = newAltezza
    }

    override fun perimetro(): Double {
        val perimetro = (base!! + altezza!!) * 2
        return perimetro
    }

    override fun area(): Double {
        val area = base!! * altezza!!
        return area
    }

    fun calcolaDiagonale() : Float {
        return -1.0f
    }

}



fun main(){

    val rettangolo1 = Rettangolo(2.0,3.0)
    val rettangolo2 = Rettangolo(4.0, 6.0)
    val rettangolo3 = Rettangolo(7.0,9.0)

    val areaPrimoRettangolo = rettangolo1.area()
    val areaSecondoRettangolo = rettangolo2.area()
    val areaTerzoRettangolo = rettangolo3.area()

    println("Area primo rettangolo: $areaPrimoRettangolo")
    println("Area secondo rettangolo: $areaSecondoRettangolo")
    println("Area terzo rettangolo: $areaTerzoRettangolo")

    val calcolaSommaAreaRettangoli = rettangolo1.area() + rettangolo2.area() + rettangolo3.area()

    println("somma area rettangoli : $calcolaSommaAreaRettangoli")

    val perimetroSecondoRettangolo = rettangolo2.perimetro()
    val perimetroTerzoRettangolo = rettangolo3.perimetro()

    println("Perimetro primo rettangolo: ${rettangolo1.perimetro()}")
    println("Perimetro secondo rettangolo: $perimetroSecondoRettangolo")
    println("Perimetro terzo rettangolo: $perimetroTerzoRettangolo")

    val calcolaSommaPerimetri = rettangolo1.perimetro() + rettangolo2.perimetro() + rettangolo3.perimetro()

    println("somma perimetri rettangoli : $calcolaSommaPerimetri")

    rettangolo1.ridimensiona(3.0, 1.0)

    val sommaAree2 = rettangolo1.area() + rettangolo2.area() + rettangolo3.area()
    val sommaPerimetri2 = rettangolo1.perimetro() + rettangolo2.perimetro() + rettangolo3.perimetro()

    println("Nuova area rettangolo1 : ${rettangolo1.area()}")
    println("somma area2 rettangoli : $sommaAree2")
    println("somma perimetri2 rettangoli : $sommaPerimetri2")




}






