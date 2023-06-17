package com.yasser.photoweather.base.presentation.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow


abstract class StateViewModel<UIModel, UIState,EVENT>(
    val initialState: UIState,
) : ViewModel() {



    val uiModel = MutableStateFlow(mapStateToUIModel(initialState, initialState))


    private var lockObject = Any()

    @Volatile
    protected var state: UIState = initialState
        @Synchronized private set(value) {
            synchronized(lockObject) {
                val oldVal = field
                field = value
                onStateUpdated(oldVal, value)
            }
        }






    protected open fun onStateUpdated(oldState: UIState, newState: UIState) {

        uiModel.value = mapStateToUIModel(oldState, newState)

    }

    protected abstract fun mapStateToUIModel(oldState: UIState, newState: UIState): UIModel

    abstract fun sendEvent(event:EVENT)



    protected open fun updateState(stateTransformer: UIState.() -> UIState) {
        this.state = stateTransformer(state)
    }

    protected open fun updateState(state: UIState) {
        this.state = state
    }




}
