package com.example.recyclerviewsqlitetest

fun main(){
    //String Template
    val name = "lsh"
    val lastName = "tt"
    println("my name is ${name + lastName}")
    println("test2\$a")

}
//val = value 변하지 않는 값 재할당불가
//var variable 변하는 수 재할당 가능
fun helloWorld() : Unit{//생략가능
    println("test")
}
//변수명 타입보다 먼저
fun add(a : Int, b : Int) : Int{
    return a+b
}
//조건식
fun maxBy(a:Int, b : Int) : Int{
    if(a < b){
        return a
    }else{
        return b
    }
}

fun maxBy2(a : Int, b : Int) = if(a > b) a else b

fun checkNum(scoreNum : Int){
    when(scoreNum){
        0 -> println("0")
        1 -> println("1")
        2,3 -> println("2 or 3")
        else -> println("test")
    }
    var b = when(scoreNum){
        1 -> 1
        2 -> 2
        else -> 3
    }

    when(scoreNum){
        in 90..100 -> println("test")
        in 10..80 -> println("reer")
        else -> println("sex")
    }
}