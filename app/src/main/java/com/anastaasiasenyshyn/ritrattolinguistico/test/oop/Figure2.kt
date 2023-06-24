package com.anastaasiasenyshyn.ritrattolinguistico.test.oop

open class Shape(var nome : String) {

    open fun area() : Float {
        println("L'area di una figura $nome, non può essere calcolata")
        return -1.0f
    }

    open fun perimetro() : Float {
        println("Il perimetro di una figura non definita, non può essere calcolata")
        return -1.0f
    }
}

class Square2(var nomeFigura : String,var lato : Float) : Shape(nomeFigura){

    override fun area() : Float {
        return (lato * lato)
    }

    override fun perimetro() : Float {
        return (lato * 4)
    }

}

class Triangle2(var nomeFigura : String,var base : Float,var altezza : Float) : Shape(nomeFigura) {

    var lato1 : Float = base
    var lato2 : Float = base

    constructor(nomeFigura: String,base: Float,alt: Float,lato1: Float,lato2: Float) : this(nomeFigura,base,alt){
        this.lato1 = lato1
        this.lato2 = lato2
    }

    override fun area() : Float {
        return (base * altezza) / 2
    }

    override fun perimetro(): Float {
        return (base + lato1 + lato2)
    }
}

class Rectangle(var nomeFigura : String,var base : Float,var altezza: Float) : Shape(nomeFigura) {

    override fun area() : Float{
        return (base * altezza)
    }

    override fun perimetro() : Float {
        return (base * 2) + (altezza * 2)
    }
}

/* Nel file Figure2.kt, implementare oltre alle figure geometriche che abbiamo visto l'ultima volta, le classi di 2 altre figure: Il Cerchio e il Rombo.
Dopodiché nel main, istanziare i relativi oggetti e invocare la printShapeInfo per stamparne l'area e e il perimetro*/

class Circle(var nomeFigura: String, var raggio:Float, var pigreco :Float) : Shape(nomeFigura){

    override fun area(): Float{
        return raggio * raggio * pigreco
    }
    override fun perimetro(): Float{
        return 2 * pigreco * raggio
    }
}

class Rhombus(var nomeFigura: String, var lato: Float, var base: Float, var altezza: Float) : Shape(nomeFigura){

    override fun area(): Float{
        return base * altezza
    }

    override fun perimetro(): Float {
        return lato * 4
    }
}

fun main(){
    val shape : Shape = Shape("Undefined")
    val square2 : Square2 = Square2("Square",5.0f)
    val triangle2EQ  :Triangle2 = Triangle2("TriangleEQ",5.0f,10.0f)
    val triangle2NEQ : Triangle2 = Triangle2("TriangleNEQ",6.0f,10.0f,15.0f,12.0f)

    val rectangle : Rectangle = Rectangle("Rectangle",10.0f,20.0f)

    val circle : Shape = Circle("Circle",3.0f,3.14159f)

    val rhombus : Shape = Rhombus("Rhombus", 6.0f,3.0f,7.0f)

    shape.area()

    printShapeInfo(square2)
    printShapeInfo(triangle2EQ)
    printShapeInfo(triangle2NEQ)
    //printShapeInfo(rectangle)
    printShapeInfo(circle)
    printShapeInfo(rhombus)

}

fun printShapeInfo(square2: Shape) {
    println("L'area di ${square2.nome} è: ${square2.area()} il perimetro di square2 è: ${square2.perimetro()}")
}

