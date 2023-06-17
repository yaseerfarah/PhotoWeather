package com.yasser.photoweather.base.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.yasser.photoweather.base.presentation.viewmodel.StateViewModel
import kotlinx.coroutines.flow.collectLatest


abstract class BaseActivity<T : ViewBinding, UIModel,UIState,EVENT,VM : StateViewModel<UIModel, UIState, EVENT>>(
) : AppCompatActivity() {


    protected abstract val viewModel: VM

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        handleOrientation()
        binding = bindView()
        setContentView(binding.root)
        initViews()
        initListener()

        lifecycleScope.launchWhenStarted  {
            viewModel.uiModel.collectLatest { uiModel ->
                render(uiModel)
            }
        }
    }

    protected abstract fun bindView(): T
    protected abstract fun handleOrientation()
    abstract fun initViews()
    abstract fun initListener()
    abstract fun render(ui: UIModel)

}
