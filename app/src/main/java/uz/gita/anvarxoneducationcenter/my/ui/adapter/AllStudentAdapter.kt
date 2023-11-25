package uz.gita.anvarxoneducationcenter.my.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.anvarxoneducationcenter.databinding.StudentItemBinding
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

class AllStudentAdapter: ListAdapter<StudentEntity, AllStudentAdapter.MyHolder>(StudentDiffUtil) {

    object StudentDiffUtil : DiffUtil.ItemCallback<StudentEntity>() {
        override fun areItemsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean  = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean = oldItem == newItem

    }

    inner class MyHolder(val binding : StudentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pos : Int) {
            binding.apply {
                tvStudentName.text = getItem(adapterPosition).firstName
                tvStudentSurname.text = getItem(adapterPosition).lastName
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(StudentItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false))

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(position)


}