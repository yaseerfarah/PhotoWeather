package com.yasser.photoweather.base.domain.interactors

abstract class SuspendUseCase<in Params, out Type> {
    abstract suspend operator fun invoke(params: Params): Type
}