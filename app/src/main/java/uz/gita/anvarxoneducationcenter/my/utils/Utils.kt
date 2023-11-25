package uz.gita.anvarxoneducationcenter.my.utils


import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uz.gita.anvarxoneducationcenter.R

fun AppCompatActivity.createFragment(id : Int , fragment : Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(id , fragment)
        .commit()
}

fun Fragment.createFragment(fragment: Fragment){
    parentFragmentManager.beginTransaction()
        .replace(R.id.container , fragment)
        .commit()
}

fun Fragment.startFragment(fragment: Fragment) {
    parentFragmentManager.beginTransaction()
        .addToBackStack(fragment::class.java.name)
        .replace(R.id.container , fragment)
        .commit()
}

fun Fragment.finish() {
    parentFragmentManager.popBackStack()
}

fun String.toast(message : String , context : Context) {
    Toast.makeText(context , message , Toast.LENGTH_LONG).show()
}