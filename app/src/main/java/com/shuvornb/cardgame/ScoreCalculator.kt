package com.shuvornb.cardgame

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
}