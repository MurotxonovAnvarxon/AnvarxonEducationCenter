package uz.gita.anvarxoneducationcenter.my.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.gita.anvarxoneducationcenter.my.data.room.entity.GroupEntity


@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGroup(groupEntity: GroupEntity)

    @Query("select * from `group`")
    fun getAllGroups(): List<GroupEntity>

    @Delete
    fun delelteGroup(groupEntity: GroupEntity)

    @Update
    fun edit(groupEntity: GroupEntity)

    @Update
    fun update(groupEntity: GroupEntity)


}