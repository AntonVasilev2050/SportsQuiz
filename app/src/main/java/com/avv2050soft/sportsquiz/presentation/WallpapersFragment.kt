package com.avv2050soft.sportsquiz.presentation

import android.Manifest
import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.avv2050soft.sportsquiz.R
import com.avv2050soft.sportsquiz.data.LocalData
import com.avv2050soft.sportsquiz.databinding.FragmentWallpapersBinding
import com.avv2050soft.sportsquiz.domain.models.WallpaperItem
import com.avv2050soft.sportsquiz.presentation.adapters.WallpapersAdapter
import com.avv2050soft.sportsquiz.presentation.utils.toastString
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import kotlin.math.abs

@AndroidEntryPoint
class WallpapersFragment : Fragment(R.layout.fragment_wallpapers) {

    private val binding by viewBinding(FragmentWallpapersBinding::bind)
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                applyWallpaper()
            } else {
                toastString("permission denied")
            }
        }
    private lateinit var viewPagerWallpaper: ViewPager2
    private val wallpapersAdapter = WallpapersAdapter(
        LocalData.wallpaperItemList,
        onClickBuy = { wallpaperItem: WallpaperItem -> onBuyClick(wallpaperItem) }
    )

    private fun onBuyClick(wallpaperItem: WallpaperItem) {
        toastString("Clicked ${wallpaperItem.price}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerWallpaper = binding.viewPagerWallpaper
        viewPagerWallpaper.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(
            MarginPageTransformer((20 * Resources.getSystem().displayMetrics.density).toInt())
        )
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPagerWallpaper.setPageTransformer(compositePageTransformer)

        viewPagerWallpaper.adapter = wallpapersAdapter

        binding.buttonBuy.setOnClickListener {
            setWallpaper()
        }
    }

    private fun setWallpaper() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.SET_WALLPAPER
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Запрашиваем разрешение
                requestPermissionLauncher.launch(Manifest.permission.SET_WALLPAPER)
            } else {
                // Если разрешение уже предоставлено, устанавливаем обои
                applyWallpaper()
            }
        } else {
            // Для версий Android ниже 6.0 не требуется запрос разрешения
            applyWallpaper()
        }
    }

    private fun applyWallpaper() {
        val wallpaperManager = WallpaperManager.getInstance(requireContext())
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.wallpaper_water_leaves)

        try {
            wallpaperManager.setBitmap(bitmap)
            toastString("wallpaper was setup successfully")
        } catch (e: IOException) {
            toastString("wallpaper setup failed ${e.message}")
        }
    }
}