package uz.gita.anvarxoneducationcenter.my.ui.screen.student.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.anvarxoneducationcenter.my.data.repository.Repository
import uz.gita.anvarxoneducationcenter.my.data.repository.impl.RepositoryImpl
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

class StudentViewModelImpl : ViewModel(), StudentViewModel {

    val repository: Repository = RepositoryImpl()
    override var pos: Int = 0
    var dataPos = 0

    override val showAddBtnLiveData = MutableLiveData<Boolean>()
    override val showAddDialogLiveData = MutableLiveData<Int>()
    override val showPlaceHolderLiveData = MutableLiveData<Boolean>()
    override val getPosStudentDataLiveData = MutableLiveData<List<StudentEntity>>()
    override val allStudentDataLiveData = MutableLiveData<List<StudentEntity>>()
    override val isFullLiveData = MutableLiveData<Boolean>()

    override fun load() {
        if (pos == -1) {
            loadData()
        } else {
            dataPos = pos
            pos = repository.getAllGroups()[pos].id
            showAddDialogLiveData.value = pos
            loadData(pos)
        }
    }

    override fun delete(studentEntity: StudentEntity) {
        repository.deleteStudent(studentEntity)
    }

    override fun setPoss(pos: Int) {
        this.pos = pos
    }

    override fun updateData() {
        loadData()
    }

    override fun updateData(pos: Int) {
        loadData(pos)
    }

    private fun loadData(pos: Int) {
        val list = repository.getStudents(pos)
        getPosStudentDataLiveData.value = list
        showAddBtnLiveData.value = true
        isFullLiveData.value = repository.getStudents(pos).size != repository.getAllGroups()[dataPos].maxCount
        showPlaceHolderLiveData.value = list.isEmpty()
    }

    private fun loadData() {
        val list = repository.getAllStudents()
        allStudentDataLiveData.value = list
        showAddBtnLiveData.value = false
        showPlaceHolderLiveData.value = list.isEmpty()
    }


}
