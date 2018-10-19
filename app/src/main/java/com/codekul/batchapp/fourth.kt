package com.codekul.batchapp

val dtFn = fun(nm : String) { // normal function
    print("hi")
}

val dtFn1 = {  nm : String -> print("hi") } // lambda

val dtFn2 = { print("") } // clojoure