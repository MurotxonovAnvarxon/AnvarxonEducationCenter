package uz.gita.anvarxoneducationcenter.my

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import uz.gita.anvarxoneducationcenter.databinding.ActivityMainBinding
import uz.gita.anvarxoneducationcenter.my.ui.screen.splash.SplashScreen
import uz.gita.anvarxoneducationcenter.my.utils.createFragment


class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)


        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(uz.gita.anvarxoneducationcenter.R.color.yellow)
        createFragment(_binding!!.container.id , SplashScreen())

    }
}