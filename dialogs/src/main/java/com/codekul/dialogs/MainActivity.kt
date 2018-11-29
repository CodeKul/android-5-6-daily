package com.codekul.dialogs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.customView
import org.jetbrains.anko.datePicker
import org.jetbrains.anko.sdk27.coroutines.onDateChanged
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btAl.setOnClickListener {
            alert {
                title = "Title"
                message = "Message"
                positiveButton( "Yes") {
                    toast("Clicked Yes")
                }
                negativeButton("No") {
                    toast("Clicked No")
                }
            }.show()
        }

        btDt.setOnClickListener {

            alert {
              customView {
                  datePicker {
                    toast("${this.year}")
                  }
              }
            }.show()
        }
    }
}
