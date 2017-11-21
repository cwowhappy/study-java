package cwowhappy.study.kotlin.coroutine

import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) {
    testNonCancellable()
}

fun testNonCancellable() = runBlocking {
    val job = launch(CommonPool) {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            run(NonCancellable) {
                println("I'm running finally")
                delay(1000L)
                println("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(2000L)
    println("main: I'm tired of waiting!")
    job.cancel()
    //delay(2000L)
    println("main: Now I can quit.")
}

fun testCooperativeCancellation1() = runBlocking<Unit> {
    val job = launch(CommonPool) {
        var nextPrintTime = 0L
        var i = 0
        while (i < 200) { // computation loop
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextPrintTime) {
                println("I'm sleeping ${i++} ... CurrentThread: ${Thread.currentThread()}")
                nextPrintTime = currentTime + 500L
            }
        }
    }
    delay(3000L)
    println("CurrentThread: ${Thread.currentThread()}")
    println("Before cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")

    val b1 = job.cancel() // cancels the job
    println("job cancel1: $b1")
    println("After Cancel, Job is alive: ${job.isActive}  Job is completed: ${job.isCompleted}")

    delay(30000L)

    val b2 = job.cancel() // cancels the job, job already canceld, return false
    println("job cancel2: $b2")

    println("main: Now I can quit.")
}

suspend fun test1() = runBlocking {
    val jobs = arrayListOf<Job>()
    jobs += launch(Unconfined) { // not confined -- will work with main thread
        println("      'Unconfined': I'm working in thread ${Thread.currentThread().name}")
    }
    jobs += launch(coroutineContext) { // context of the parent, runBlocking coroutine
        println("'coroutineContext': I'm working in thread ${Thread.currentThread().name}")
    }
    jobs += launch(CommonPool) { // will get dispatched to ForkJoinPool.commonPool (or equivalent)
        println("      'CommonPool': I'm working in thread ${Thread.currentThread().name}")
    }
    jobs += launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
        println("          'newSTC': I'm working in thread ${Thread.currentThread().name}")
    }
    jobs.forEach { it.join() }
}