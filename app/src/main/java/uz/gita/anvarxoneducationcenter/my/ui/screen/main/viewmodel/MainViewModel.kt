package uz.gita.anvarxoneducationcenter.my.ui.screen.main.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.anvarxoneducationcenter.my.data.room.entity.GroupEntity
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity
import uz.gita.anvarxoneducationcenter.my.ui.adapter.StudentAdapter

interface MainViewModel {
    val showAllGroupsLiveData: LiveData<List<GroupEntity>>
    val showPlaceHolderLiveData: LiveData<Boolean>
    val showMessageLiveData: LiveData<String>
    val moveToAddStudentsScreenLiveData: LiveData<Unit>
    val moveToStudentsScreenLiveData: LiveData<Unit>
    val showDialogAddLiveData: LiveData<Unit>

    fun updateData()
    fun clickAdd()
    fun clickAll()
    fun clickGroup()
    fun deletgroup(groupEntity: GroupEntity)
    fun clickEdit()
    fun edir(groupEntity: GroupEntity)
    fun deleteStudent(studentEntity: StudentEntity)
    fun editStudent(studentEntity: StudentEntity)

}