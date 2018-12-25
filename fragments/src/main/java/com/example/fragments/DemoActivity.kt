package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DemoActivity : AppCompatActivity() {

    var mob : String = ""
    var fn : ((mb : String) -> Unit)? = null

   /*
    // Java Style : 1
   interface MobEv {
        fun onMobileChange( mob : String)
    }

    lateinit var mobEv : MobEv*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        loadFrag(Frag2())
    }

    fun loadFrag(frag : androidx.fragment.app.Fragment) {
        val txn = supportFragmentManager.beginTransaction()
        txn.replace(R.id.frmLt, frag)
        txn.commit()
    }
}

class Frag1() : androidx.fragment.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rtVw = inflater.inflate(R.layout.frag1, container, false)
        val at = activity as DemoActivity
        at.fn = {
            rtVw.findViewById<Button>(R.id.btOk).text =  it
        }
        /*
         // Java Style : 1
        at.mobEv = object : DemoActivity.MobEv {
            override fun onMobileChange(mob: String) {
                rtVw.findViewById<Button>(R.id.btOk).text =  at.mob
            }
        }*/
        rtVw.findViewById<Button>(R.id.btOk).setOnClickListener {
            at.loadFrag(Frag3.getInstance(at.mob))
        }
        return rtVw
    }
}

class Frag2() : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rtVw = inflater.inflate(R.layout.frag2, container, false)
        rtVw.findViewById<EditText>(R.id.etMb).addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        (activity as DemoActivity).mob = s.toString()
                        /*
                         // Java Style : 1
                        (activity as DemoActivity).mobEv.onMobileChange(s.toString())
                        */

                        (activity as DemoActivity). fn?.invoke(s.toString())
                    }
                }
        )
        return rtVw
    }
}

class Frag3() : androidx.fragment.app.Fragment() {
    companion object {
        fun getInstance(mob : String) :  Frag3 {

            val fr = Frag3()

            val bnd = Bundle()
            bnd.putString("mob", mob)

            fr.arguments = bnd

            return fr
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rtVw = inflater.inflate(R.layout.frag3, container, false)
        rtVw.findViewById<TextView>(R.id.txtMb).text = arguments?.getString("mob")
        return rtVw
    }
}
