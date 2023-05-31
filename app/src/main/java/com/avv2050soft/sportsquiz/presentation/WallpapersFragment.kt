package com.avv2050soft.sportsquiz.presentation

import android.Manifest
import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.avv2050soft.sportsquiz.R
import com.avv2050soft.sportsquiz.databinding.FragmentWallpapersBinding
import com.avv2050soft.sportsquiz.presentation.utils.toastString
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class WallpapersFragment : Fragment(R.layout.fragment_wallpapers) {

    private val binding by viewBinding(FragmentWallpapersBinding::bind)
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Разрешение получено, устанавливаем обои
                applyWallpaper()
            } else {
                toastString("permission denied")
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                // Запрашиваем разрешение с помощью новой системы обратного вызова разрешений
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
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.windows_95)

        try {
            wallpaperManager.setBitmap(bitmap)
            toastString("wallpaper was setup successfully")
        } catch (e: IOException) {
            e.printStackTrace()
            toastString("wallpaper setup failed ${e.message}")
        }
    }
}