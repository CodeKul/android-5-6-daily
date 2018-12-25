package com.codekul.androddaily

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_common.*

class CommonActivity : AppCompatActivity() {

    lateinit var mg : MgCl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        imgCmn.setImageResource(intent?.extras!!.getInt("img"))
        imgCmn.setOnClickListener {
            mg = magicColor()
            imgCmn.setColorFilter(
                    Color.rgb(mg.r.toInt(), mg.g.toInt(),mg.b.toInt())
            )
        }
    }

    fun magicColor()  = MgCl(
            (Math.random() * 256).toFloat(),
            (Math.random() * 256).toFloat(),
            (Math.random() * 256).toFloat()
        )

    override fun onBackPressed() {

        val bkInt = Intent()
        bkInt.putExtra("r", mg.r.toInt())
        bkInt.putExtra("g", mg.g.toInt())
        bkInt.putExtra("b", mg.b.toInt())

        setResult(Activity.RESULT_OK, bkInt)

        super.onBackPressed()
    }
    data class MgCl(
            val r : Float,
            val g : Float,
            val b : Float
    )
}


