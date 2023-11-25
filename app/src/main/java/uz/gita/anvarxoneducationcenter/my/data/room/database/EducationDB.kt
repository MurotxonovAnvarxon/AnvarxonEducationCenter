package uz.gita.anvarxoneducationcenter.my.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.anvarxoneducationcenter.my.data.room.dao.GroupDao
import uz.gita.anvarxoneducationcenter.my.data.room.dao.StudentDao
import uz.gita.anvarxoneducationcenter.my.data.room.entity.GroupEntity
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity


@Database(entities = [GroupEntity::class , StudentEntity::class] , version = 1)
abstract class EducationDB : RoomDatabase() {

    abstract fun getGroupDao() : GroupDao
    abstract fun getStudentDao() : StudentDao

    companion object {

        var educationDB : EducationDB ?= null

        fun init(context : Context) {
            educationDB = Room.databaseBuilder(context , EducationDB::class.java , "education.db")
                .allowMainThreadQueries()
                .build()
        }

        fun getInstance() : EducationDB = educationDB!!

    }

}