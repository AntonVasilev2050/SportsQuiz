package com.avv2050soft.sportsquiz.presentation

import android.Manifest
import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.PictureDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.avv2050soft.sportsquiz.R
import com.avv2050soft.sportsquiz.data.LocalData
import com.avv2050soft.sportsquiz.databinding.DialogConfirmPurchaseBinding
import com.avv2050soft.sportsquiz.databinding.FragmentWallpapersBinding
import com.avv2050soft.sportsquiz.domain.models.Player
import com.avv2050soft.sportsquiz.domain.models.WallpaperItem
import com.avv2050soft.sportsquiz.presentation.GameViewModel.Companion.gameScore
import com.avv2050soft.sportsquiz.presentation.adapters.WallpapersAdapter
import com.avv2050soft.sportsquiz.presentation.utils.toastString
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import kotlin.math.abs

@AndroidEntryPoint
class WallpapersFragment : Fragment(R.layout.fragment_wallpapers) {

    private var pictureDrawable = R.drawable.wallpaper_water_leaves
    private val binding by viewBinding(FragmentWallpapersBinding::bind)
    private val viewModel  by viewModels<WallpapersViewModel>()
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                applyWallpaper(pictureDrawable)
            } else {
                toastString(getString(R.string.permission_denied))
            }
        }
    private lateinit var viewPagerWallpaper: ViewPager2
    private val wallpapersAdapter = WallpapersAdapter(
        LocalData.wallpaperItemList,
        onClickBuy = { wallpaperItem: WallpaperItem -> onBuyClick(wallpaperItem) }
    )

    private fun onBuyClick(wallpaperItem: WallpaperItem) {
        conformPurchase(wallpaperItem)
    }

    private fun conformPurchase(wallpaperItem: WallpaperItem) {
        val confirmDialog = BottomSheetDialog(requireContext())
        val bindingDialog = DialogConfirmPurchaseBinding.inflate(layoutInflater)
        confirmDialog.setContentView(bindingDialog.root)
        bindingDialog.buttonNo.setOnClickListener { confirmDialog.dismiss() }
        bindingDialog.buttonYes.setOnClickListener {
            if (gameScore >= wallpaperItem.price) {
                gameScore -= wallpaperItem.price
                viewModel.insertPlayerIntoDb(Player(1, "Player", gameScore))
                pictureDrawable = wallpaperItem.pictureDrawable
                setWallpaper(pictureDrawable)
            } else {
                toastString(getString(R.string.not_enough_points_to_purchase))
            }
            confirmDialog.dismiss()
            binding.textViewPointsCount.text = gameScore.toString()
        }
        confirmDialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewPointsCount.text = gameScore.toString()
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
            page.scaleY = (0.70f + r * 0.30f)
        }
        viewPagerWallpaper.setPageTransformer(compositePageTransformer)
        viewPagerWallpaper.adapter = wallpapersAdapter

    }

    private fun setWallpaper(pictureDrawable: Int) {
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
                applyWallpaper(pictureDrawable)
            }
        } else {
            // Для версий Android ниже 6.0 не требуется запрос разрешения
            applyWallpaper(pictureDrawable)
        }
    }

    private fun applyWallpaper(pictureDrawable: Int) {
        val wallpaperManager = WallpaperManager.getInstance(requireContext())
        val bitmap = BitmapFactory.decodeResource(resources, pictureDrawable)

        try {
            wallpaperManager.setBitmap(bitmap)
            toastString("wallpaper was setup successfully")
        } catch (e: IOException) {
            toastString("wallpaper setup failed ${e.message}")
        }
    }
}