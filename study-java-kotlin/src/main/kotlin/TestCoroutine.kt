package cwowhappy.study.java.kotlin

import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val sum = List(100000) {
            //async { provide() }
            provide()
        //}.map { it.await()
        }.reduce{ sum, value -> sum + value}
        println("The answer is $sum")
    }
    println("耗时: $time ms")
}

suspend fun provide(): Int {
    delay(10L)
    return 1
}

suspend fun doSomeUsefulOne(): Int {
    delay(1000L)
    return 1
}

suspend fun doSomeUsefulTwo(): Int {
    delay(1000L)
    return 2
}
