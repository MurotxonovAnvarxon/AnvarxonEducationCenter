package uz.gita.anvarxoneducationcenter.my.ui.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import uz.gita.anvarxoneducationcenter.databinding.DialogAddStudentBinding
import uz.gita.anvarxoneducationcenter.databinding.DialogEditBinding
import uz.gita.anvarxoneducationcenter.my.data.repository.Repository
import uz.gita.anvarxoneducationcenter.my.data.repository.impl.RepositoryImpl
import uz.gita.anvarxoneducationcenter.my.data.room.entity.GroupEntity
import uz.gita.anvarxoneducationcenter.my.data.room.entity.StudentEntity

class DialogEditStudent(private val studentEntity: StudentEntity) : DialogFragment() {
    var _binding: DialogAddStudentBinding? = null
    val binding get() = _binding!!
    val repositoryGroup: Repository = RepositoryImpl()
    var onClick: (() -> Unit)? = null

    fun setAdapterBlock(block: () -> Unit) {
        onClick = block
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogAddStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.inputName.setText(studentEntity.firstName)
        binding.inputLastName.setText(studentEntity.lastName)
        binding.buttonAdd.setText("Edit")

        binding.buttonAdd.setOnClickListener {
            if (binding.inputName.text.toString() != "" && binding.inputName.text.toString().length > 3) {
                val gr = StudentEntity(
                    id = studentEntity.id,
                    firstName = binding.inputName.text.toString(),
                    lastName = binding.inputLastName.text.toString(),
                    groupId = studentEntity.groupId
                )
                repositoryGroup.editStudent(gr)
                onClick?.invoke()
                dismiss()
            } else {
                binding.inputName.error = "Guruhingizning nomi 3 ta harfdan ko'proq bo'lsin"
            }
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
