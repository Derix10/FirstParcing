package com.example.a6monthproject.ui.playlist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a6monthproject.databinding.PlaylistItemBinding
import com.example.a6monthproject.model.Item

class AdapterPlaylist(private var myContext: Context, private var list : ArrayList<Item>) : RecyclerView.Adapter<AdapterPlaylist.PlaylistHolder>() {



   inner class PlaylistHolder(private var binding: PlaylistItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "CheckResult")
        fun onBind(playList: Item) {

            binding.titleItem.text = playList.snippet?.title.toString()
            binding.descriptionItem.text = "${playList.contentDetails?.itemCount} series"
            Glide
                .with(myContext)
                .load(playList.snippet?.thumbnails?.medium?.url)
                .centerCrop()
                .into(binding.imgItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
       val binding = PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int = list.size
}