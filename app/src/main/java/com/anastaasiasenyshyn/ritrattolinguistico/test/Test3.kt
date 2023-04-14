package com.anastaasiasenyshyn.ritrattolinguistico.test


fun main() {

    val array = mutableListOf(4,23,15,21,3,80,1800)

    print("Unordered: ")
    array.forEach { element ->
        print("$element ")
    }

    bubleSort(array)

    print("\nOrdered: ")
    array.forEach { element ->
        print("$element ")
    }



}

fun bubleSort(unsortedArray: MutableList<Int>){

    for(i in 0.. (unsortedArray.size -1)){
        for(j in i + 1 .. (unsortedArray.size -1)){
            if (unsortedArray[j] < unsortedArray[i]){
                val tmp = unsortedArray[j]
                unsortedArray[j] = unsortedArray[i]
                unsortedArray[i] = tmp
            }
        }
    }

}
