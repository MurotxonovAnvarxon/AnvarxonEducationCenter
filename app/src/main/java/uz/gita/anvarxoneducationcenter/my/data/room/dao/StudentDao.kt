package uz.gita.anvarxoneducationcenter.my.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity


@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStudents(studentEntity: StudentEntity)


    @Delete
    fun delete(studentEntity: StudentEntity)

    @Query("delete from student where group_id = :id")
    fun deleteStudents(id: Int)

    @Query("select * from student")
    fun getAllStudents(): List<StudentEntity>

    @Query("select * from student where group_id = :id")
    fun getStudents(id: Int): List<StudentEntity>
}