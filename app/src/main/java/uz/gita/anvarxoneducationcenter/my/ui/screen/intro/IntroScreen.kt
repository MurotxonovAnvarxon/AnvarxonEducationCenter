package uz.gita.anvarxoneducationcenter.my.ui.screen.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import uz.gita.anvarxoneducationcenter.R
import uz.gita.anvarxoneducationcenter.databinding.ActivityIntroFragmentBinding

class IntroScreen: Fragment(R.layout.intropage2) {
    private lateinit var binding : ActivityIntroFragmentBinding
    private var adapter : IntroAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = IntroAdapter(parentFragmentManager , lifecycle)
        
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityIntroFragmentBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPager2.adapter = adapter
    }
}