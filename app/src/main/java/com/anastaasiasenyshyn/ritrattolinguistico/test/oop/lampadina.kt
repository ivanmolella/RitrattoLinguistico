package com.anastaasiasenyshyn.ritrattolinguistico.test.oop

import kotlin.system.exitProcess

/*Scrivere la classe Lampadina i cui oggetti rappresentano delle lampadine elettriche. Una
lampadina pu`o essere accesa, spenta o rotta, e mette a disposizione due sole operazioni: stato() che
restituisce una stringa che indica lo stato corrente della lampadina e click() che ne cambia lo stato da
accesa a spenta o da spenta a accesa o la rompe. Una lampadina si rompe dopo un certo numero di click
definito dal fabbricante. La classe deve contenere:
• Una o pi`u variabili d’istanza che descrivano opportunamente lo stato della lampadina
• Un opportuno costruttore
• I metodi previsti
Per testare la classe, scrivere un programma TestLampadina che crea un oggetto della classe Lampadina
che ammetta un numero massimo di click deciso dall’utente e poi iterativamente offre all’utente la pos-
sibilit`a di invocare una delle due funzionalit`a (visualizzando l’esito dell’operazione, nel caso di stato())
o di terminare l’esecuzione.*/

open class lampadina() {
    val lampadina: String? = null
    var interuttore: Int? = null
    var accesa: Int? = null
    var spenta: Int? = null
    var rotta: Int? = null

    open fun click() {

        println("Premi su 0 per accendere la lampadina e su 1 per spegnerla")
        interuttore = readLine()!!.toInt()
        when (interuttore) {
            0 -> accesa.toString()
            1 -> spenta.toString()
            else -> {
                println("il numero inserito non è comforme, scegli un numero indicato nella spiegazione")
            }
        }
        /*println("Inserisci numero click")
        val numeroClick = readLine()!!.toInt()
        println("Numero click iserito $numeroClick")

        if (interuttore!! >= numeroClick!!) {

            rotta
        } else {
            interuttore
        }*/
        println("la lampadina è ${interuttore.toString()}")
    }

    open fun stato() {
        accesa.toString()
        spenta.toString()
        rotta.toString()
        when (interuttore) {
            accesa -> println("La lampadina è ${stato()}")
            spenta -> println("La lampadina è ${stato()}")
            rotta -> println("La lampadina è ${stato()}")
        }
    }

    open fun sceltaOpzione() {
        println("Scrivi 0 per aprire click, 1 per aprire stato e 2 per uscire")
        val scelta: Int = readLine()!!.toInt()
        when(scelta) {
            0 -> click()
            1 -> stato()
        }
        do {
            sceltaOpzione()
        } while (scelta < 2)



    }
}


    fun main() {
        val lampadina = lampadina()
        lampadina.sceltaOpzione()

    }

