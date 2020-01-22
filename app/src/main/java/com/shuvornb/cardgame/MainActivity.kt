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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDisplayCards.setOnClickListener {

            cardRandomizer.initializeDeckOfCards()
            var randomCards = cardRandomizer.getFourRandomCards()

            if(randomCards.isEmpty()) Toast.makeText(this, "All cards have been displayed!", Toast.LENGTH_SHORT).show()
            else {
                val assetsBitmap1: Bitmap? = getBitmapFromAssets(randomCards[0] + ".png")
                imgViewCard1.setImageBitmap(assetsBitmap1)

                val assetsBitmap2: Bitmap? = getBitmapFromAssets(randomCards[1] + ".png")
                imgViewCard2.setImageBitmap(assetsBitmap2)

                val assetsBitmap3: Bitmap? = getBitmapFromAssets(randomCards[2] + ".png")
                imgViewCard3.setImageBitmap(assetsBitmap3)

                val assetsBitmap4: Bitmap? = getBitmapFromAssets(randomCards[3] + ".png")
                imgViewCard4.setImageBitmap(assetsBitmap4)
            }
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
