package uz.jaxongir.finalexam.data.pref

import android.content.Context
import android.content.SharedPreferences

class MyPref {

    private constructor(context: Context){
        myShared = context.getSharedPreferences("myPref", Context.MODE_PRIVATE)
    }

    companion object {

        var myPref : MyPref ?= null
        var myShared : SharedPreferences ?= null

        fun init(context: Context) {
            myPref = MyPref(context)
        }

        fun getMyPref() : SharedPreferences = myShared!!

        fun getInstance() : MyPref = myPref!!

    }

    fun isLaunched(): Boolean? {
        return myShared?.getBoolean("check", true)
    }

    fun setLaunch(launched : Boolean){
        myShared?.edit()?.putBoolean("check", launched)?.apply()
    }

}