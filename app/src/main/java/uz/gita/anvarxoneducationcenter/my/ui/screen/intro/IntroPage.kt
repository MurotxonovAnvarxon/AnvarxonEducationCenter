package uz.gita.anvarxoneducationcenter.my.ui.screen.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.gita.anvarxoneducationcenter.databinding.ActivityIntroFragmentBinding
import uz.gita.anvarxoneducationcenter.databinding.Intropage1Binding
import uz.gita.anvarxoneducationcenter.databinding.Intropage2Binding
import uz.gita.anvarxoneducationcenter.databinding.Intropage3Binding
import uz.gita.anvarxoneducationcenter.my.ui.screen.main.MainViewFragment
import uz.gita.anvarxoneducationcenter.my.utils.createFragment
import uz.jaxongir.finalexam.data.pref.MyPref

class IntroPage:Fragment() {



    private var pos : Int ?= null
    private lateinit var binding : Intropage3Binding
    private val myPref by lazy { MyPref.getMyPref() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pos = arguments?.getInt("pos")
        return when(pos) {
            0 -> {
                Intropage1Binding.inflate(inflater , container , false).root
            }
            1 -> {
                Intropage2Binding.inflate(inflater , container , false).root
            }
            else -> {
                 binding=Intropage3Binding.inflate(inflater , container , false)
                binding.root
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (pos == 2) {
            binding.start.setOnClickListener {
                myPref.edit().putBoolean("check" , false).apply()
                createFragment(MainViewFragment())
            }
        }
    }

}