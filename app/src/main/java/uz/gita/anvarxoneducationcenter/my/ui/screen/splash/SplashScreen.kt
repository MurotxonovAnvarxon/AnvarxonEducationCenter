package uz.gita.anvarxoneducationcenter.my.ui.screen.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import uz.gita.anvarxoneducationcenter.R
import uz.gita.anvarxoneducationcenter.my.ui.screen.intro.IntroScreen
import uz.gita.anvarxoneducationcenter.my.ui.screen.main.MainViewFragment
import uz.gita.anvarxoneducationcenter.my.utils.createFragment
import uz.jaxongir.finalexam.data.pref.MyPref

class SplashScreen : Fragment(R.layout.screen_splash) {

    private val myPref = MyPref.getMyPref()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        object : CountDownTimer(3000 , 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                if (myPref.getBoolean("check" , true)) {
                    createFragment(IntroScreen())
                }else{
                    Log.d("TTT" , "TAG")
                    createFragment(MainViewFragment())
                }
            }

        }.start()
    }
}