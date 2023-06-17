package com.yasser.photoweather.base.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.yasser.photoweather.base.presentation.viewmodel.StateViewModel
import kotlinx.coroutines.flow.collectLatest


abstract class BaseFragment<BINDING : ViewBinding, UIModel, UIState,EVENT,VM : StateViewModel<UIModel, UIState, EVENT>>(
) : Fragment() {

    protected abstract val viewModel: VM

    private var _binding: BINDING? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListener()

        lifecycleScope.launchWhenStarted {
            viewModel.uiModel.collectLatest { uiModel ->
                render(uiModel)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun bindView(): BINDING

    abstract fun initViews()
    abstract fun initListener()
    abstract fun render(ui: UIModel)

}
