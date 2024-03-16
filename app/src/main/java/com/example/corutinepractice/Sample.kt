package com.example.corutinepractice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import kotlin.math.log

fun main() {

    runBlocking { question1() }
}

fun logging(msg: String) {
    println("${Thread.currentThread().name} - ${LocalDateTime.now()} : $msg")
}

@OptIn(DelicateCoroutinesApi::class)
suspend fun question1() {
    logging("1")
    runBlocking {
        logging("2")
        val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
            logging("3")
            coroutineScope {
                delay(600)
                logging("4")
            }
            delay(200)
            logging("5")
        }
        coroutineScope {
            logging("6")
            lazyJob.join()
            delay(400)
            logging("7")
        }
        logging("8")
    }
    logging("9")
    delay(1000)

}




