package com.geekbrains.poplibrary

class CountersModel() {

    private val counterValues = mutableListOf<Int>()

    var counters : Int
        get() = counterValues.size
        set(value) {
            counterValues.clear()
            for (i in 0.. value.dec())
                counterValues.add(0)
        }
    fun getCurrent(index: Int) = counterValues[index]

    fun next(index: Int): Int {
        counterValues[index]++
        return getCurrent(index)
    }
}