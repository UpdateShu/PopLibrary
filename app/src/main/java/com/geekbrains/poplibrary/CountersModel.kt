package com.geekbrains.poplibrary

class CountersModel(counters: Int) {

    private val counterValues = mutableListOf<Int>()

    init {
        for (i in 0..counters.dec())
            counterValues.add(0)
    }

    fun getCurrent(index: Int) = counterValues[index]

    fun next(index: Int): Int {
        counterValues[index]++
        return getCurrent(index)
    }

    /*fun set(index: Int, value: Int) {
        counterValues[index] = value
    }*/
}