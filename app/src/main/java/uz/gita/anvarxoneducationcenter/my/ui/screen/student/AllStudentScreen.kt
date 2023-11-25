package uz.gita.anvarxoneducationcenter.my.ui.screen.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import uz.gita.anvarxoneducationcenter.databinding.ScreenAllStudentBinding
import uz.gita.anvarxoneducationcenter.my.ui.adapter.AllStudentAdapter
import uz.gita.anvarxoneducationcenter.my.ui.screen.student.viewmodel.AllStudentViewModelImpl
import uz.gita.anvarxoneducationcenter.my.utils.finish

class AllStudentScreen : Fragment() {
    private var _binding: ScreenAllStudentBinding? = null
    private val binding get() = _binding!!
    private var pos = 0
    private lateinit var vm: AllStudentViewModelImpl

    private val adapter by lazy { AllStudentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[AllStudentViewModelImpl::class.java]
        if (arguments?.getInt("pos", 0) == null) {
            pos = -1
        } else {
            pos = arguments?.getInt("pos", 0)!!
        }

        vm.load()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = ScreenAllStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
    }

    private fun initVM() {


        binding.back.setOnClickListener {
         finish()
        }
        vm.getPosStudentDataLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.recycleAllStudent.adapter = adapter
        }
        vm.allStudentDataLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.recycleAllStudent.adapter = adapter
        }
        vm.showPlaceHolderLiveData.observe(viewLifecycleOwner){
            binding.noData.isVisible=it
        }
    }


}