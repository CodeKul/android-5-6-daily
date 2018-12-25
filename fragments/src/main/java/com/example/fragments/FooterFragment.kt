package com.example.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


/**
 * A simple [Fragment] subclass.
 *
 */
class FooterFragment : androidx.fragment.app.Fragment() {
    companion object {

        fun getInstance() : FooterFragment {

            val frag = FooterFragment()
            val bn = Bundle()
            frag.arguments = bn

            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val hostActivity = activity as MainActivity

        val rtVw = inflater.inflate(R.layout.fragment_footer, container, false)

        rtVw.findViewById<Button>(R.id.btnRotate).setOnClickListener {
            hostActivity.loadFrag(RotateFragment())
        }

        rtVw.findViewById<Button>(R.id.btnFade).setOnClickListener {
            hostActivity.loadFrag(
                    ContentFragment.getInstance(R.drawable.ic_donation)
            )
        }

        rtVw.findViewById<Button>(R.id.btnScale).setOnClickListener {
            hostActivity.loadFrag(FooterFragment())
        }


        return rtVw
    }
}
