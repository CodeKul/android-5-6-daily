package com.codekul.dialogs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onDateChanged

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btAl.setOnClickListener {
            alert {
                title = "Title"
                message = "Message"
                positiveButton("Yes") {
                    toast("Clicked Yes")
                }
                negativeButton("No") {
                    toast("Clicked No")
                }
            }.show()
        }

        btDt.setOnClickListener {

            alert {
                lateinit var dt : DatePicker
                customView {
                    dt = datePicker {   }
                }

                yesButton {di ->
                    toast("${dt.dayOfMonth} - ${dt.month} - ${dt.year}")
                    di.dismiss()
                }
            }.show()
        }
    }
}
