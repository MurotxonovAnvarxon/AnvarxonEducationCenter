package uz.gita.anvarxoneducationcenter.my.app

import android.app.Application
import uz.gita.anvarxoneducationcenter.my.data.room.database.EducationDB
import uz.jaxongir.finalexam.data.pref.MyPref

class App  : Application() {

    override fun onCreate() {
        super.onCreate()

        MyPref.init(this)
        EducationDB.init(this)

    }

}