package com.codekul.batchapp

// 1) functions can be assigned to variables
// 2) functions can be passed as parameter
// 3) functions can be returned from another function

val fnSpl = fun(prm: () -> Unit) {}

val fnLmd = { println("Hi") }

fun fnPrm(prm: () -> Unit) {}

fun fnRtn(): () -> Unit {
    //return  fnLmd
    return fun() { }
//    return {
//        println("Returned Function")
//    }
}

fun main(args: Array<String>) {
    fnSpl(fun() {}) //  call
    fnLmd()

    fnPrm(fnLmd)
    fnPrm(fun() { })
    fnPrm({ })
    fnPrm { }

    val fnIn = fnRtn()
    fnIn()

    fnRtn()()
}