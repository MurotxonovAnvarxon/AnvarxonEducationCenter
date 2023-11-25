package uz.gita.anvarxoneducationcenter.my.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.anvarxoneducationcenter.R
import uz.gita.anvarxoneducationcenter.databinding.ItemGroupBinding
import uz.gita.anvarxoneducationcenter.my.data.repository.impl.RepositoryImpl
import uz.gita.anvarxoneducationcenter.my.data.room.database.EducationDB
import uz.gita.anvarxoneducationcenter.my.data.room.entity.GroupEntity

class GroupAdapter : ListAdapter<GroupEntity, GroupAdapter.MyHolder>(GroupDiffUtil) {

    var onClickItem: ((Int) -> Unit)? = null
    val studentDao = EducationDB.getInstance().getStudentDao()
    private var setdelet: ((contact: GroupEntity) -> Unit)? = null
    private var setedit: ((contact: GroupEntity) -> Unit)? = null
    private var edit: ((contact: GroupEntity) -> Unit)? = null


    fun setOnClickItems(block: (Int) -> Unit) {
        onClickItem = block
    }

    fun SetDelete(block: (GroupEntity) -> Unit) {
        setdelet = block
    }

    fun SetEdit(block: (GroupEntity) -> Unit) {
        setedit = block
    }


    object GroupDiffUtil : DiffUtil.ItemCallback<GroupEntity>() {
        override fun areItemsTheSame(oldItem: GroupEntity, newItem: GroupEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GroupEntity, newItem: GroupEntity): Boolean =
            oldItem == newItem
    }

    inner class MyHolder(val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickItem?.invoke(adapterPosition)
            }



            binding.more.setOnClickListener { v ->
                val popupMenu = PopupMenu(v.context, v)
                popupMenu.inflate(R.menu.moremenu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete -> {
                            setdelet?.invoke(getItem(adapterPosition))
                        }

                        R.id.edit -> {
                            setedit?.invoke(getItem(adapterPosition))
                        }

                    }

                    return@setOnMenuItemClickListener true
                }
                popupMenu.show()
            }


        }


        val repository = RepositoryImpl()
        fun bind(pos: Int) {
            if (studentDao.getStudents(repository.getAllGroups()[pos].id).size == 0) {
                binding.viewBack.setBackgroundResource(R.drawable.bg_red)
            } else if (studentDao.getStudents(pos + 1).size != getItem(pos).maxCount) {
                binding.viewBack.setBackgroundResource(R.drawable.bg_yellow)
            } else {
                binding.viewBack.setBackgroundResource(R.drawable.bg_green)
            }
            binding.apply {
                tvGroupName.text = getItem(pos).name
                count.text = "${studentDao.getStudents(repository.getAllGroups()[pos].id).size}/"
                countMax.text = getItem(pos).maxCount.toString()
            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
            MyHolder(ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(position)

}