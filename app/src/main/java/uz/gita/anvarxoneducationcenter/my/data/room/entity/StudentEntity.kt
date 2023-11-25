package uz.gita.anvarxoneducationcenter.my.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    @ColumnInfo("group_id")
    val groupId: Int,
)
