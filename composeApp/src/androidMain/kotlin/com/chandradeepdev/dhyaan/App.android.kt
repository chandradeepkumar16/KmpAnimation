package  com.chandradeepdev.kmpanimation

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.chandradeepdev.dhyaan.App

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val systemBarColor = Color.TRANSPARENT
        setContent {
            val view = LocalView.current
            var isLightStatusBars by remember { mutableStateOf(false) }
            if (!view.isInEditMode) {
                LaunchedEffect(isLightStatusBars) {
                    val window = (view.context as Activity).window
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                    window.statusBarColor = systemBarColor
                    window.navigationBarColor = systemBarColor
                    WindowCompat.getInsetsController(window, window.decorView).apply {
                        isAppearanceLightStatusBars = isLightStatusBars
                    }
                }
            }
            App(systemAppearance = { isLight -> isLightStatusBars = isLight })
        }
    }
}

//internal actual fun openUrl(url: String?) {
//    val uri = url?.let { Uri.parse(it) } ?: return
//    val intent = Intent().apply {
//        action = Intent.ACTION_VIEW
//        data = uri
//        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//    }
//    AndroidApp.INSTANCE.startActivity(intent)
//}