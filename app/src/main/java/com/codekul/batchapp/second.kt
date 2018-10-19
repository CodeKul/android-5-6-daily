package com.codekul.batchapp

fun myFn() { }

val fn: () -> Unit = ::myFn
val anFn: (bl: Boolean) -> Int = fun(bl: Boolean): Int {
    if(bl) return 89
    return 45
}

fun main(args: Array<String>) {

    myFn()
    fn()
    val dt = anFn(true)
}