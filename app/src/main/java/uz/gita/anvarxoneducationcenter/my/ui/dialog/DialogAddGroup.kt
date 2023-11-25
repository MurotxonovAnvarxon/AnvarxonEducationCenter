package uz.gita.anvarxoneducationcenter.my.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import uz.gita.anvarxoneducationcenter.databinding.DialogAddBinding
import uz.gita.anvarxoneducationcenter.my.data.repository.Repository
import uz.gita.anvarxoneducationcenter.my.data.repository.impl.RepositoryImpl
import uz.gita.anvarxoneducationcenter.my.data.room.entity.GroupEntity
import uz.gita.anvarxoneducationcenter.my.ui.screen.main.viewmodel.MainViewModel
import uz.gita.anvarxoneducationcenter.my.ui.screen.main.viewmodel.MainViewModelImpl

class DialogAddGroup : DialogFragment() {

    var _binding : DialogAddBinding ?= null
    val binding get() = _binding!!
    val repositoryGroup : Repository = RepositoryImpl()
    var onClick : (() -> Unit) ?= null

    fun setAdapterBlock(block : () -> Unit) {
        onClick = block
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonAdd.setOnClickListener {
            if (binding.inputName.text.toString() != "" && binding.inputDescription.text.toString() != "" && binding.inputMaxSize.text.toString() != "") {
                repositoryGroup.addGroup(
                    GroupEntity(
                        0,
                        binding.inputName.text.toString(),
                        binding.inputDescription.text.toString(),
                        binding.inputMaxSize.text.toString().toInt()
                    )
                )
                onClick?.invoke()
                dismiss()
            } else {
               if(binding.inputName.text.length<3) binding.inputName.error = "Length should be more than 3"
                if(binding.inputDescription.text.length<3)binding.inputDescription.error = "Length should be more than 3"
                if(binding.inputMaxSize.text.length<5)binding.inputMaxSize.error = "Min size should be 5"
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
