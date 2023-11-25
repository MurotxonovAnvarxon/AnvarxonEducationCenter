package uz.gita.anvarxoneducationcenter.my.ui.screen.student.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

interface StudentViewModel {

    val showAddBtnLiveData : LiveData<Boolean>
    val showAddDialogLiveData : LiveData<Int>
    val showPlaceHolderLiveData : LiveData<Boolean>
    val getPosStudentDataLiveData : LiveData<List<StudentEntity>>
    val allStudentDataLiveData : LiveData<List<StudentEntity>>
    val isFullLiveData : LiveData<Boolean>
    val pos : Int

    fun updateData()
    fun updateData(pos : Int)
    fun load()
    fun delete(studentEntity: StudentEntity)
    fun setPoss(pos : Int)
}