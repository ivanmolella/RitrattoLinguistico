package com.anastaasiasenyshyn.ritrattolinguistico.test

/*Scrivere una classe Rettangolo i cui oggetti rappresentano rettangoli. Lo stato interno di
un rettangolo e' dato dai valori della base e dell’altezza. Un rettangolo deve mettere a disposizione tre
operazioni: ridimensiona() che prende come parametri due nuovi valori di base e altezza e aggiorna
lo stato, perimetro() che restituisce il perimetro e area() che restituisce l’area. Prevedere inoltre un
costruttore che inizializza base e altezza del rettangolo.
Per testare la classe, scrivere un programma TestRettangolo che crea tre rettangoli (oggetti della
classe Rettangolo) ne calcola la somma delle aree e la somma dei perimetri (stampando i risultati),
ridimensiona uno dei tre rettangoli e ristampa le somme.*/

open class Rettangolo(base: Double, altezza: Double) {
    var base: Double? = null
    var altezza: Double? = null

    init {
        this.base = base
        this.altezza = altezza
    }

    open fun ridimensiona(newBase: Double, newAltezza: Double): Double{
        this.base = newBase
        this.altezza = newAltezza
        return newBase

    }

    fun perimetro(rettangolo: Rettangolo): Double {
        val perimetro = (base!! + altezza!!) * 2
        return perimetro
    }

    fun area(rettangolo : Rettangolo): Double {
        val area = base!! * altezza!!
        return area
    }

}



fun main(){

    val rettangolo1 = Rettangolo(2.0,3.0)
    val rettangolo2 = Rettangolo(4.0, 6.0)
    val rettangolo3 = Rettangolo(7.0,9.0)

    val areaPrimoRettangolo = rettangolo1.area(rettangolo1)
    val areaSecondoRettangolo = rettangolo2.area(rettangolo2)
    val areaTerzoRettangolo = rettangolo3.area(rettangolo3)

    println("Area primo rettangolo: $areaPrimoRettangolo")
    println("Area secondo rettangolo: $areaSecondoRettangolo")
    println("Area terzo rettangolo: $areaTerzoRettangolo")

    val calcolaSommaAreaRettangoli = rettangolo1.area(rettangolo1) + rettangolo2.area(rettangolo2) + rettangolo3.area(rettangolo3)

    println("somma area rettangoli : $calcolaSommaAreaRettangoli")

    val perimetroPrimoRettangolo = rettangolo1.perimetro(rettangolo1)
    val perimetroSecondoRettangolo = rettangolo2.perimetro(rettangolo2)
    val perimetroTerzoRettangolo = rettangolo3.perimetro(rettangolo3)

    println("Perimetro primo rettangolo: $perimetroPrimoRettangolo")
    println("Perimetro secondo rettangolo: $perimetroSecondoRettangolo")
    println("Perimetro terzo rettangolo: $perimetroTerzoRettangolo")

    val calcolaSommaPerimetri = rettangolo1.perimetro(rettangolo1) + rettangolo2.perimetro(rettangolo2) + rettangolo3.perimetro(rettangolo3)

    println("somma perimetri rettangoli : $calcolaSommaPerimetri")

    val ridimensionarettangolo = rettangolo1.ridimensiona(3.0, 1.0)
    println("$ridimensionarettangolo")


}






