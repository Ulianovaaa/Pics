package com.izzzya.pics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.izzzya.pics.classes.SharedPrefs
import com.izzzya.pics.classes.ZoomClass
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay

class PicOrigFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pic_orig, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mDecorView = requireActivity().window.decorView
        var hiddenUI = true

        fun hideSystemUI(){
            mDecorView.apply {
                systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

            }
            hiddenUI = true
        }

        fun showSystemUI(){
            mDecorView.apply {}
            hiddenUI = false
        }

        hideSystemUI()
        val zoomableIV = view.findViewById<ZoomClass>(R.id.largeImage)
        val link = SharedPrefs.LINK
        Picasso.get().load(link)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(zoomableIV)


        zoomableIV.setOnClickListener{
            when(hiddenUI){
                true -> showSystemUI()
                false -> hideSystemUI()
            }
        }

    }


}