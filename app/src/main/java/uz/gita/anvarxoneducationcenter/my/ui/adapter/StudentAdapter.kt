package uz.gita.anvarxoneducationcenter.my.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.anvarxoneducationcenter.R
import uz.gita.anvarxoneducationcenter.databinding.StudentItemBinding
import uz.gita.anvarxoneducationcenter.my.data.repository.impl.RepositoryImpl
import uz.gita.anvarxoneducationcenter.my.data.room.database.EducationDB
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity


class StudentAdapter : ListAdapter<StudentEntity, StudentAdapter.MyHolder>(GroupDiffUtil) {

    var onClickItem: ((Int) -> Unit)? = null
    val studentDao = EducationDB.getInstance().getStudentDao()
    private var setdeletStudent: ((contact: StudentEntity) -> Unit)? = null
    private var seteditStudent: ((contact: StudentEntity) -> Unit)? = null
    private var edit: ((contact: StudentEntity) -> Unit)? = null


    fun SetDeleteStudent(block: (StudentEntity) -> Unit) {
        setdeletStudent = block
    }

    fun SetEdit(block: (StudentEntity) -> Unit) {
        seteditStudent = block
    }


    object GroupDiffUtil : DiffUtil.ItemCallback<StudentEntity>() {
        override fun areItemsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean =
            oldItem == newItem
    }

    inner class MyHolder(val binding: StudentItemBinding) : RecyclerView.ViewHolder(binding.root) {

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
                            setdeletStudent?.invoke(getItem(adapterPosition))
                        }
                        R.id.edit -> {
                            seteditStudent?.invoke(getItem(adapterPosition))
                        }

                    }

                    return@setOnMenuItemClickListener true
                }
                popupMenu.show()
            }


        }


        val repository = RepositoryImpl()
        fun bind(pos: Int) {

            binding.apply {
                tvStudentName.text = getItem(pos).firstName
                tvStudentSurname.text = getItem(pos).lastName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(StudentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(position)

}

