package uz.gita.anvarxoneducationcenter.my.ui.screen.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.gita.anvarxoneducationcenter.databinding.ScreenStudentBinding
import uz.gita.anvarxoneducationcenter.my.ui.adapter.StudentAdapter
import uz.gita.anvarxoneducationcenter.my.ui.dialog.DialogAddStudent
import uz.gita.anvarxoneducationcenter.my.ui.dialog.DialogEditStudent
import uz.gita.anvarxoneducationcenter.my.ui.screen.student.viewmodel.StudentViewModel
import uz.gita.anvarxoneducationcenter.my.ui.screen.student.viewmodel.StudentViewModelImpl

class StudentScreen : Fragment() {

    private var _binding: ScreenStudentBinding? = null
    private val binding get() = _binding!!
    private var pos = 0
    private lateinit var vm: StudentViewModel
    private val adapter by lazy { StudentAdapter() }
    private var dialog: DialogAddStudent = DialogAddStudent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[StudentViewModelImpl::class.java]
        if (arguments?.getInt("pos", 0) == null) {
            pos = -1
        } else {
            pos = arguments?.getInt("pos", 0)!!
        }

        vm.setPoss(pos)
        vm.load()

//        adapter.SetDeleteStudent {
//            vm.de(it)
//            vm.updateData()
//        }
        adapter.SetEdit {
            val dialog = DialogEditStudent(it)
            dialog.setAdapterBlock {
                vm.updateData()
            }
            dialog.show(parentFragmentManager, null)
        }

        adapter.SetDeleteStudent {
            vm.delete(it)
            vm.updateData(it.groupId)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = ScreenStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initVM()
        checkAdd()

        binding.btnAdd.setOnClickListener {
            dialog.show(parentFragmentManager, null)
            dialog = DialogAddStudent()
            initVM()
            checkAdd()
        }
    }

    private fun checkAdd() {
        vm.isFullLiveData.observe(viewLifecycleOwner) {
            binding.btnAdd.isVisible = it
        }
    }

    private fun initVM() {

        vm.showAddBtnLiveData.observe(viewLifecycleOwner) {
            binding.btnAdd.isVisible = it
        }
        vm.getPosStudentDataLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.recycle.adapter = adapter
        }
        vm.allStudentDataLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.recycle.adapter = adapter
        }
        vm.showAddDialogLiveData.observe(viewLifecycleOwner) {
            val id = it
            dialog.id(id)
            dialog.setAdapterBlock {
                vm.updateData(id)//id
            }
        }
        vm.showPlaceHolderLiveData.observe(viewLifecycleOwner) {
            binding.noData.isVisible = it
        }
    }

}