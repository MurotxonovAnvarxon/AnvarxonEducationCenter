package uz.gita.anvarxoneducationcenter.my.data.repository

import uz.gita.anvarxoneducationcenter.my.data.room.entity.GroupEntity
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

interface Repository {
    fun addGroup(groupEntity: GroupEntity)
    fun addStudent(studentEntity: StudentEntity)
    fun updateGroup(groupEntity: GroupEntity)
    fun updateStudent(studentEntity: StudentEntity)
    fun deleteGroup(groupEntity: GroupEntity)
    fun deleteStudents(id: Int)
    fun editGroup(groupEntity: GroupEntity)
    fun getAllGroups(): List<GroupEntity>
    fun getAllStudents(): List<StudentEntity>
    fun deleteStudent(studentEntity: StudentEntity)
    fun editStudent(studentEntity: StudentEntity)
    fun getStudents(groupId: Int): List<StudentEntity>
}