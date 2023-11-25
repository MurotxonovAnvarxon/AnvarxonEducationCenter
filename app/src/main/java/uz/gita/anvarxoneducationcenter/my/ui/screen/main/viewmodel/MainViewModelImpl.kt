package uz.gita.anvarxoneducationcenter.my.ui.screen.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.anvarxoneducationcenter.my.data.repository.Repository
import uz.gita.anvarxoneducationcenter.my.data.repository.impl.RepositoryImpl
import uz.gita.anvarxoneducationcenter.my.data.room.entity.GroupEntity
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

class MainViewModelImpl : ViewModel(), MainViewModel {

    val repository: Repository = RepositoryImpl()

    override val showAllGroupsLiveData = MutableLiveData<List<GroupEntity>>()
    override val showPlaceHolderLiveData = MutableLiveData<Boolean>()
    override val showMessageLiveData = MutableLiveData<String>()
    override val moveToAddStudentsScreenLiveData = MutableLiveData<Unit>()
    override val moveToStudentsScreenLiveData = MutableLiveData<Unit>()
    override val showDialogAddLiveData = MutableLiveData<Unit>()

    init {
        loadData()
    }

    override fun updateData() {
        loadData()
    }


    override fun clickAdd() {
        showDialogAddLiveData.value = Unit
    }

    override fun clickGroup() {
        moveToAddStudentsScreenLiveData.value = Unit
    }

    override fun deletgroup(groupEntity: GroupEntity) {
        repository.deleteGroup(groupEntity)
        repository.deleteStudents(groupEntity.id)
    }

    override fun clickEdit() {
    }

    override fun edir(groupEntity: GroupEntity) {
        repository.editGroup(groupEntity)
    }

    override fun deleteStudent(studentEntity: StudentEntity) {
        repository.deleteStudent(studentEntity)
    }

    override fun editStudent(studentEntity: StudentEntity) {
        repository.editStudent(studentEntity)
    }

    override fun clickAll() {
        moveToStudentsScreenLiveData.value = Unit
    }

    fun loadData() {
        val list = repository.getAllGroups()
        showAllGroupsLiveData.value = list
        showPlaceHolderLiveData.value = list.isEmpty()
    }

}