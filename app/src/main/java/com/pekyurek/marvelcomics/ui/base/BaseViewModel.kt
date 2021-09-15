package com.pekyurek.marvelcomics.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pekyurek.marvelcomics.data.repository.ResultStatus
import com.pekyurek.marvelcomics.domain.exception.base.BaseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val showErrorPopup = MutableLiveData<BaseException>()

    fun showLoading(visibility: Boolean) = loading.postValue(visibility)

    fun <T> request(
        flow: Flow<ResultStatus<T>>,
        onSuccess: ((data: T) -> Unit)? = null,
        onError: ((t: BaseException) -> Unit)? = null,
        forceLoadingHidden: Boolean = false,
        errorPopupCanVisible: Boolean = true
    ) = viewModelScope.launch(Dispatchers.IO) {
        flow.collect { result ->
            when (result) {
                is ResultStatus.Loading -> showLoading(result.show && forceLoadingHidden.not())
                is ResultStatus.Success -> onSuccess?.invoke(result.data)
                is ResultStatus.Exception -> {
                    if (errorPopupCanVisible) {
                        showErrorPopup.postValue(result.exception)
                    }
                    onError?.invoke(result.exception)
                }
            }
        }
    }
}