package com.pekyurek.marvelcomics.ui.character.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pekyurek.marvelcomics.domain.model.character.Character
import com.pekyurek.marvelcomics.domain.usecase.character.CharacterListUseCase
import com.pekyurek.marvelcomics.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterListUseCase: CharacterListUseCase
) : BaseViewModel() {

    val pagingCharacterList = MutableLiveData<PagingData<Character>>()

    fun loadPagingCharacterList() = viewModelScope.launch(Dispatchers.IO) {
        characterListUseCase.pagingFlow().cachedIn(viewModelScope)
            .distinctUntilChanged()
            .collectLatest {
                PagingData
                pagingCharacterList.postValue(it)
            }
    }
}