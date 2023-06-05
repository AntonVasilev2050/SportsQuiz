package com.avv2050soft.sportsquiz.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avv2050soft.sportsquiz.databinding.ItemWallpaperBinding
import com.avv2050soft.sportsquiz.domain.models.WallpaperItem
import com.bumptech.glide.Glide

class WallpapersAdapter(
    private val wallpapers: List<WallpaperItem>,
    private val onClickBuy: (WallpaperItem) -> Unit
) : RecyclerView.Adapter<WallpapersAdapter.WallpaperViewHolder>(){

    inner class WallpaperViewHolder(val binding: ItemWallpaperBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        return WallpaperViewHolder(
            ItemWallpaperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = wallpapers.size

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(imageViewWallpaper.context)
                .load(wallpapers[position].pictureDrawable)
                .into(imageViewWallpaper)
            textViewWallpaperPrice.text = wallpapers[position].price.toString()

            buttonBuyWallpaper.setOnClickListener { onClickBuy.invoke(wallpapers[position]) }
        }
    }
}