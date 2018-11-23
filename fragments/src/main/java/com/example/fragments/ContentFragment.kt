package com.example.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class ContentFragment : Fragment() {

    companion object {
        fun getInstance(imgId : Int) : ContentFragment {

            val frag = ContentFragment()

            val bndl = Bundle()
            bndl.putInt("imageId", imgId)
            frag.arguments = bndl

            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.imgVw)
                .setImageResource(
                        arguments!!.getInt("imageId")
                )
    }


}
