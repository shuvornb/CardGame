package com.shuvornb.cardgame

import android.util.Log

class ScoreCalculator {

    var globalScore = 0
    var singleIterationScore = 0
    var lastRoundScore = 0

    fun addScore(cardInfo: String) {
        singleIterationScore += convertCardInfoToPoint(cardInfo)
    }

    fun convertCardInfoToPoint(cardInfo: String): Int {
        return cardInfo.replace("[^0-9]".toRegex(), "").toInt()
    }

    fun calculateScore() {
        lastRoundScore = 0
        if(isPrime(singleIterationScore)) {
            globalScore += singleIterationScore
            lastRoundScore = singleIterationScore
        }
        singleIterationScore = 0
    }

    fun calculateHighestPrime(deckOfCards: List<String>): Int {

        var listOfSets = powerset(deckOfCards)

        Log.i("SCORE", listOfSets.toString())

        var largestPrime = 0

        for (list in listOfSets) {
            var sum = 0
            for(item in list) {
                sum += convertCardInfoToPoint(item)
            }
            if(isPrime(sum)) if(sum > largestPrime) largestPrime = sum
        }

        Log.i("SCORE", largestPrime.toString())
        return largestPrime
    }

    fun isPrime(number: Int): Boolean {
        if(number == 1) return false
        var flag = false
        for (i in 2..number / 2) {
            if (number % i == 0) {
                flag = true
                break
            }
        }
        return !flag
    }

    fun <T> powerset(list: Collection<T>): List<List<T>> {
        var ps: MutableList<List<T>> = ArrayList()
        ps.add(ArrayList())   // add the empty set

        // for every item in the original list
        for (item in list) {
            val newPs = ArrayList<List<T>>()

            for (subset in ps) {
                // copy all of the current powerset's subsets
                newPs.add(subset)

                // plus the subsets appended with the current item
                val newSubset = ArrayList(subset)
                newSubset.add(item)
                newPs.add(newSubset)
            }

            // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
            ps = newPs
        }
        return ps
    }
}