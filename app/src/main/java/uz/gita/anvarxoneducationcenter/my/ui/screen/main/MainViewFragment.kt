package uz.gita.anvarxoneducationcenter.my.ui.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.gita.anvarxoneducationcenter.R
import uz.gita.anvarxoneducationcenter.databinding.FragmentHomeBinding
import uz.gita.anvarxoneducationcenter.my.ui.adapter.GroupAdapter
import uz.gita.anvarxoneducationcenter.my.ui.dialog.DialogAddGroup
import uz.gita.anvarxoneducationcenter.my.ui.dialog.DialogEdit
import uz.gita.anvarxoneducationcenter.my.ui.screen.main.viewmodel.MainViewModel
import uz.gita.anvarxoneducationcenter.my.ui.screen.main.viewmodel.MainViewModelImpl
import uz.gita.anvarxoneducationcenter.my.ui.screen.student.AllStudentScreen
import uz.gita.anvarxoneducationcenter.my.ui.screen.student.StudentScreen
import uz.gita.anvarxoneducationcenter.my.utils.startFragment

class MainViewFragment : Fragment() {

    lateinit var vm: MainViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { GroupAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[MainViewModelImpl::class.java]
        vm.moveToAddStudentsScreenLiveData.observe(this) { startFragment(StudentScreen()) }
        vm.showDialogAddLiveData.observe(this) {
            val dialog = DialogAddGroup()
            dialog.setAdapterBlock {
                vm.updateData()
            }
            dialog.show(parentFragmentManager, null)
        }



        vm.updateData()

        adapter.SetDelete {
            vm.deletgroup(it)
            vm.updateData()
        }
        adapter.SetEdit {
            val dialog = DialogEdit(it)
            dialog.setAdapterBlock {
                vm.updateData()
            }
            dialog.show(parentFragmentManager, null)
        }










    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        vm.updateData()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        initAdapter()
        click()
        clickAll()
        clickStudentItem()
        vm.updateData()
    }

    private fun clickStudentItem() {
        adapter.setOnClickItems {
            val bundle = Bundle()
            bundle.putInt("pos", it)

            val fragment = StudentScreen()
            fragment.arguments = bundle
            startFragment(fragment)
        }
    }

    private fun clickAll() {
        binding.team.setOnClickListener {
            startFragment(AllStudentScreen())
        }
    }


    private fun initVM() {
        vm.showAllGroupsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        vm.showPlaceHolderLiveData.observe(viewLifecycleOwner) {
            binding.noData.isVisible = it
        }
    }

    private fun initAdapter() {
        binding.recycle.adapter = adapter
    }

    private fun click() {
        binding.btnadd.setOnClickListener {
            vm.clickAdd()
        }
    }




}
