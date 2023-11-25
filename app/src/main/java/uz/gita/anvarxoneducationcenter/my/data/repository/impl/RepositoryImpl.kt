package uz.gita.anvarxoneducationcenter.my.data.repository.impl

import uz.gita.anvarxoneducationcenter.my.data.repository.Repository
import uz.gita.anvarxoneducationcenter.my.data.room.database.EducationDB
import uz.gita.anvarxoneducationcenter.my.data.room.entity.GroupEntity
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

class RepositoryImpl : Repository {

    val groupDao = EducationDB.getInstance().getGroupDao()
    val studentDao = EducationDB.getInstance().getStudentDao()

    override fun addGroup(groupEntity: GroupEntity) {
        groupDao.addGroup(groupEntity)
    }

    override fun addStudent(studentEntity: StudentEntity) {
        studentDao.addStudents(studentEntity)
    }

    override fun updateGroup(groupEntity: GroupEntity) {
        groupDao.addGroup(groupEntity)
    }

    override fun updateStudent(studentEntity: StudentEntity) {
        studentDao.addStudents(studentEntity)
    }

    override fun deleteGroup(groupEntity: GroupEntity) {
        groupDao.delelteGroup(groupEntity)
    }

    override fun deleteStudents(id: Int) {
        studentDao.deleteStudents(id)
    }

    override fun editGroup(groupEntity: GroupEntity) {
        groupDao.edit(groupEntity)
    }

    override fun getAllGroups(): List<GroupEntity> =
        groupDao.getAllGroups()


    override fun getAllStudents(): List<StudentEntity> = studentDao.getAllStudents()
    override fun deleteStudent(studentEntity: StudentEntity) {
        studentDao.delete(studentEntity)
    }

    override fun editStudent(studentEntity: StudentEntity) {
    studentDao.addStudents(studentEntity)
    }

    override fun getStudents(groupId: Int): List<StudentEntity> = studentDao.getStudents(groupId)
}