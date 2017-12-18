package cwowhappy.study.java.kotlin

import kotlin.coroutines.experimental.buildSequence

fun main(args: Array<String>) {

    val lazySeq = buildSequence {
        print("START ")
        for (i in 1..5) {
            yield(i)
            print("STEP ")
        }
        print("END")
    }

// Print the first three elements of the sequence
    lazySeq.forEach { print("$it ") }
}

class Person(private var name: String) {
    fun say(word: String) {
        println("Person $name say $word")
    }
}