package com.pekyurek.marvelcomics.ui.character.detail

import androidx.lifecycle.MutableLiveData
import com.pekyurek.marvelcomics.domain.model.request.CharacterComicsRequest
import com.pekyurek.marvelcomics.domain.usecase.ComicsListUseCase
import com.pekyurek.marvelcomics.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val comicsListUseCase: ComicsListUseCase,
) : BaseViewModel() {

    private val maxComicsSize = 10

    private val afterYear2005 by lazy {
        Calendar.getInstance().apply {
            set(Calendar.YEAR, 2005)
            set(Calendar.DAY_OF_YEAR, 1)
        }.time
    }

    val comicsText = MutableLiveData<String>()

    fun loadCharacterComics(characterId: Int) {
        request(
            flow = comicsListUseCase.execute(CharacterComicsRequest(characterId)),
            onSuccess = { list ->
                val filterList = list
                    .filter { comics ->
                        comics.digitalPurchaseDateMs?.after(afterYear2005) == true
                    }
                    .sortedByDescending { it.digitalPurchaseDateMs }
                    .take(maxComicsSize)
                val responseText =
                    filterList.joinToString("\n") { it.title + " - " + it.digitalPurchaseDate }
                comicsText.postValue(responseText)
            }
        )
    }
}