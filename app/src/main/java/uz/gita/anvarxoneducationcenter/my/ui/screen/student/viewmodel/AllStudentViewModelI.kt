package uz.gita.anvarxoneducationcenter.my.ui.screen.student.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

interface AllStudentViewModelI {
    val showPlaceHolderLiveData : LiveData<Boolean>
    val getPosStudentDataLiveData : LiveData<List<StudentEntity>>
    val allStudentDataLiveData : LiveData<List<StudentEntity>>
    val pos : Int

    fun updateData()
    fun load()
    fun setPoss(pos : Int)
}