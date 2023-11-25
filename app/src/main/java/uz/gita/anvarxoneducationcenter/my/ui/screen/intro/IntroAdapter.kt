package uz.gita.anvarxoneducationcenter.my.ui.screen.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.anvarxoneducationcenter.my.ui.screen.intro.IntroPage

class IntroAdapter(fm : FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm , lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putInt("pos" , position)
        val fragment  = IntroPage()
        fragment.arguments = bundle
        return fragment
    }
}