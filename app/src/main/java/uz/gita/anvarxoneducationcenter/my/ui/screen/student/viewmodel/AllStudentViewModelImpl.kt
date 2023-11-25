package uz.gita.anvarxoneducationcenter.my.ui.screen.student.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.anvarxoneducationcenter.my.data.repository.Repository
import uz.gita.anvarxoneducationcenter.my.data.repository.impl.RepositoryImpl
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

class AllStudentViewModelImpl : ViewModel(), AllStudentViewModelI {
    override val showPlaceHolderLiveData = MutableLiveData<Boolean>()
    override val getPosStudentDataLiveData = MutableLiveData<List<StudentEntity>>()
    override val allStudentDataLiveData = MutableLiveData<List<StudentEntity>>()
    override var pos: Int = 0
    private val repository: Repository = RepositoryImpl()



    override fun updateData() {
        loadData()
    }

    override fun load() {
        loadData()
    }

    override fun setPoss(pos: Int) {
        this.pos = pos
    }

    private fun loadData() {
        val list = repository?.getAllStudents()
        allStudentDataLiveData.value = list
        showPlaceHolderLiveData.value = list?.isEmpty()
    }
}