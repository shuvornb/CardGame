package com.shuvornb.cardgame

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var cardRandomizer = CardRandomizer()
    var scoreCalculator = ScoreCalculator()
    var largestPrime = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDisplayCards.setOnClickListener {

            cardRandomizer.initializeDeckOfCards()
            var randomCards = cardRandomizer.getFourRandomCards()

            if(randomCards.isEmpty()) Toast.makeText(this, "All cards have been displayed!", Toast.LENGTH_SHORT).show()
            else {

                largestPrime = scoreCalculator.calculateHighestPrime(randomCards)

                val assetsBitmap1: Bitmap? = getBitmapFromAssets(randomCards[0] + ".png")
                imgViewCard1.setImageBitmap(assetsBitmap1)
                imgViewCard1.setTag(imgViewCard1.id, randomCards[0])

                val assetsBitmap2: Bitmap? = getBitmapFromAssets(randomCards[1] + ".png")
                imgViewCard2.setImageBitmap(assetsBitmap2)
                imgViewCard2.setTag(imgViewCard2.id, randomCards[1])

                val assetsBitmap3: Bitmap? = getBitmapFromAssets(randomCards[2] + ".png")
                imgViewCard3.setImageBitmap(assetsBitmap3)
                imgViewCard3.setTag(imgViewCard3.id, randomCards[2])

                val assetsBitmap4: Bitmap? = getBitmapFromAssets(randomCards[3] + ".png")
                imgViewCard4.setImageBitmap(assetsBitmap4)
                imgViewCard4.setTag(imgViewCard4.id, randomCards[3])
            }
        }

        imgViewCard1.setOnClickListener {
            //Toast.makeText(this, imgViewCard1.getTag(imgViewCard1.id).toString() + "Button", Toast.LENGTH_SHORT).show()
            scoreCalculator.addScore(imgViewCard1.getTag(imgViewCard1.id).toString())
        }

        imgViewCard2.setOnClickListener {
            scoreCalculator.addScore(imgViewCard2.getTag(imgViewCard2.id).toString())
        }

        imgViewCard3.setOnClickListener {
            scoreCalculator.addScore(imgViewCard3.getTag(imgViewCard3.id).toString())
        }

        imgViewCard4.setOnClickListener {
            scoreCalculator.addScore(imgViewCard4.getTag(imgViewCard4.id).toString())
        }

        btnCalculateScore.setOnClickListener {
            scoreCalculator.calculateScore()
            val scoreOfThisRound = scoreCalculator.lastRoundScore.toString()
            val scoreSoFar = scoreCalculator.globalScore.toString()

           // Log.i("SCORE", scoreCalculator.globalScore)
            val score =
                "You got $scoreOfThisRound points in this round. You could have got $largestPrime points. Your total score so far $scoreSoFar"
            txtViewPoints.text = score
        }
    }

    // Custom method to get assets folder image as bitmap
    private fun getBitmapFromAssets(fileName: String): Bitmap? {
        return try {
            BitmapFactory.decodeStream(assets.open(fileName))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
