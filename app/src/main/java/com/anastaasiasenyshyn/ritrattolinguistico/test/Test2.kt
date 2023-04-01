package com.anastaasiasenyshyn.ritrattolinguistico.test

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

}

class Strada(var nome : String,var larghezza: Double,var ponte: Ponte,var tunnel: Tunnel){
    fun isVeicoloAllowed(veicolo: Veicolo) : Boolean{
        var isAllowed = (veicolo.getLarghezzaVeicolo()!! <  larghezza!!)
        if (isAllowed){
            isAllowed = (ponte.isVeicoloAllowed(veicolo) && tunnel.isVeicoloAllowed(veicolo))
        }
        return isAllowed
    }

    fun getNomeStrada() : String{
        return nome
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

fun main() {

    val veicolo = Veicolo(1.5,2.0,1500.0)
    val ponte = Ponte(4.0,10000.0)
    val tunnel = Tunnel(1.0,15.0)

    val strada = Strada("Via Cristoforo Colombo",10.0,ponte,tunnel)

    val isVeicoloAllowedInStreet = strada.isVeicoloAllowed(veicolo)

    println("Il veicolo è abilitato per passare in via ${strada.getNomeStrada()}: $isVeicoloAllowedInStreet")


}