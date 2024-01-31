package com.izzzya.pics.classes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.izzzya.pics.R
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class PreviewAdapter(private val context: Context,
                     private val dataset: List<String>
): RecyclerView.Adapter<PreviewAdapter.PreviewViewHolder>() {

    class PreviewViewHolder(view: View): RecyclerView.ViewHolder(view){
        val iv = view.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        val mLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.preview_item, parent, false)
        return PreviewViewHolder(mLayout)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        val item = dataset[position]
        fun loadFromCache(){
            Picasso.get().load(item)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image)
                .resize(150, 150)
                .centerCrop()
                .into(holder.iv)
        }
        fun loadOnline(){
            Picasso.get().load(item)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image)
                .resize(150, 150)
                .centerCrop()
                .into(holder.iv)
        }

        try {
            if (SharedPrefs.getPicsList().isNotEmpty()){
                loadFromCache()
            }else loadOnline()

        }catch (ex: Exception){
            //hide img
            holder.itemView.visibility = View.GONE
            Log.e("image loading ex", ex.message.toString())

        }

        holder.iv.setOnClickListener(object : OnClickListener{
            override fun onClick(p0: View?) {
                SharedPrefs.LINK = item
                p0!!.findNavController().navigate(R.id.action_global_picOrigFragment)
            }
        })

    }



}