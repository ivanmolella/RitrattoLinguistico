package com.anastaasiasenyshyn.ritrattolinguistico.test.oop

open class GeometricShape {

    open fun area() : Float {
        println("Figura indefinita, area non calcolabile")

        return -1.0f
    }

    open fun perimetro() : Float{
        println("Figura indefinita, perimetro non calcolabile")
        return -1.0f
    }

    open fun getName() : String {
        return "Unkonwn"
    }
}

class Square(var nome : String,var lato : Float) : GeometricShape() {

    override fun area() : Float {
        return lato * lato
    }

    override fun perimetro() : Float {
        return lato * 4
    }

    override fun getName(): String {
        return nome
    }
 }

class Triangle(private var nome:String,var base:Float,var altezza: Float) : GeometricShape() {

    var lato1 : Float = base
    var lato2 : Float = base

    constructor(nome:String,base:Float,lato1 : Float, lato2: Float,altezza:Float) : this(nome,base,altezza){
        this.lato1 = lato1
        this.lato2 = lato2
        this.altezza = altezza
    }

    override fun area() : Float {
        return (base * altezza) / 2
    }

    override fun perimetro() : Float {
        return base + lato1 + lato2
    }

    override fun getName(): String {
        return nome
    }

}


    fun main() {

        val figuraGeometrica = GeometricShape()
        val square = Square("Square",4.0f)
        val triangle = Triangle("Triangle",5.0f, lato1 = 10.0f, lato2 = 10.0f, altezza = 15.toFloat())
        val triangleEquilatero = Triangle("Triangle Equilateral",5.0f,10.0f)

        figuraGeometrica.area()
        figuraGeometrica.perimetro()

        println("Shape: ${square.getName()} area: ${square.area()} perimetro: ${square.perimetro()}")
        println("Shape: ${triangle.getName()} area: ${triangle.area()} perimetro: ${triangle.perimetro()}")
        println("Shape: ${triangleEquilatero.getName()} area: ${triangleEquilatero.area()} perimetro: ${triangleEquilatero.perimetro()}")

    }
