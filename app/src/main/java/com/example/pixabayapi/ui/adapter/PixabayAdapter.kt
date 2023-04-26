package com.example.pixabayapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixabayapi.ImageModel
import com.example.pixabayapi.databinding.ItemImageBinding

class PixabayAdapter(val list: ArrayList<ImageModel>) :
    RecyclerView.Adapter<PixabayAdapter.PixabayViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayViewHolder {
        return PixabayViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )


        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PixabayViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addImages(listImages: ArrayList<ImageModel>) {
        list.addAll(listImages)
    }


    inner class PixabayViewHolder(var binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun bind(imageModel: ImageModel) {
            binding.imageView.load(imageModel.largeImageURL)
        }

    }

}