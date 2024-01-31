package com.izzzya.pics

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.izzzya.pics.classes.DataSource
import com.izzzya.pics.classes.PreviewAdapter
import com.izzzya.pics.classes.SharedPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class PreviewFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val rv = view.findViewById<RecyclerView>(R.id.previewRV)
        val lm = FlexboxLayoutManager(requireContext())
        lm.flexDirection = FlexDirection.ROW
        lm.justifyContent = JustifyContent.CENTER
        rv.layoutManager = lm
        suspend fun showPics() {
            withContext(Dispatchers.Main) {
                try {
                    rv.adapter = PreviewAdapter(requireContext(), SharedPrefs.getPicsList())
                }catch(e: Exception){
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        if (SharedPrefs.getPicsList().isNotEmpty()){
            rv.adapter = PreviewAdapter(requireContext(), SharedPrefs.getPicsList())
        }else{
            CoroutineScope(Main).launch {
                launch {
                    DataSource.getImageLinks()
                }
                launch {
                    //min delay 1000 for first launch
                    delay(1000)
                    showPics()
                }
            }.invokeOnCompletion {
                Log.d("CoroutineJob", "all jobs completed")

            }
        }


    }

}