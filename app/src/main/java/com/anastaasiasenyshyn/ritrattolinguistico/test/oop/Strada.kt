package com.anastaasiasenyshyn.ritrattolinguistico.test.oop

class Veicolo(altezza : Double,larghezza : Double,peso : Double) {
        var altezza : Double? = null
        var larghezza : Double? = null
        var peso : Double? = null

        init {
            this.altezza = altezza
            this.larghezza = larghezza
            this.peso = peso
        }

        fun getAltezzaVeicolo() : Double = this.altezza!!
        fun getLarghezzaVeicolo() : Double = this.larghezza!!
        fun getPesoVeicolo() : Double = this.peso!!
        override fun toString(): String {
            return "Veicolo(altezza=$altezza, larghezza=$larghezza, peso=$peso)"
        }
    }

class Ponte(var larghezza: Double,var pesoSostenuto: Double){
    fun isVeicoloAllowed(veicolo: Veicolo) : Boolean{
        val isAllowed = (veicolo.getLarghezzaVeicolo() < larghezza && veicolo.getPesoVeicolo() < pesoSostenuto)
        if (isAllowed){
            println("Il Veicolo può passare il ponte")
        }else{
            println("Il Veicolo non può passare il ponte")
        }
        return isAllowed
    }
}

class Tunnel(var altezza : Double,var larghezza: Double){
    fun isVeicoloAllowed(veicolo: Veicolo) : Boolean{
        val isAllowed =  (veicolo.getAltezzaVeicolo() < altezza && veicolo.getLarghezzaVeicolo() < larghezza)
        if (isAllowed){
            println("Il Veicolo può passare il tunnel")
        }else{
            println("Il Veicolo non può passare il tunnel")
        }
        return isAllowed
    }
}

class Strada(var nome : String, var larghezza: Double, var ponte: Ponte, var tunnel: Tunnel){
    fun isVeicoloAllowed(veicolo: Veicolo) : Boolean{
        var isAllowed = (veicolo.getLarghezzaVeicolo()!! <  larghezza!!)
        if (isAllowed){
            println("Il veicolo può passare la strada $nome")
            isAllowed = (ponte.isVeicoloAllowed(veicolo) && tunnel.isVeicoloAllowed(veicolo))
        }else{
            println("Il veicolo non può passare la strada $nome")
        }
        return isAllowed
    }

    fun getNomeStrada() : String{
        return nome
    }
}

    fun main() {
        val veicolo = Veicolo(9.5,2.0,8.0)
        val ponte = Ponte(4.0,15.0)
        val tunnel = Tunnel(8.0,12.0)

        val strada = Strada("Via Ostiense",15.0,ponte,tunnel)

        val isVeicoloAllowed = strada.isVeicoloAllowed(veicolo)

        if (isVeicoloAllowed){
            println("Il veicolo può transitare in via: ${strada.getNomeStrada()}")
        }else{
            println("Il veicolo non può transitare in via: ${strada.getNomeStrada()}")
        }
    }