package uz.gita.anvarxoneducationcenter.my.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "group")
data class GroupEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int ,
    val name : String ,
    val description : String ,
    @ColumnInfo("max_count")
    val maxCount : Int
)