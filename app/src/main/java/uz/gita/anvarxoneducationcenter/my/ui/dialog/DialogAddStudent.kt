package uz.gita.anvarxoneducationcenter.my.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import uz.gita.anvarxoneducationcenter.databinding.DialogAddStudentBinding
import uz.gita.anvarxoneducationcenter.my.data.repository.Repository
import uz.gita.anvarxoneducationcenter.my.data.repository.impl.RepositoryImpl
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

class DialogAddStudent : DialogFragment() {

    var _binding : DialogAddStudentBinding ?= null
    val binding get() = _binding!!
    val repositoryStudent : Repository = RepositoryImpl()
    var onClick : (() -> Unit) ?= null
    var postion = 0

    fun id(id : Int) {
        postion = id
    }

    fun setAdapterBlock(block : () -> Unit) {
        onClick = block
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddStudentBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonAdd.setOnClickListener {
            repositoryStudent.addStudent(StudentEntity(0 , binding.inputName.text.toString() , binding.inputLastName.text.toString() , postion))
            onClick?.invoke()
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}