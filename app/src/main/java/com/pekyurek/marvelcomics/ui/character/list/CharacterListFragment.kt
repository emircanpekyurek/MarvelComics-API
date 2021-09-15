package com.pekyurek.marvelcomics.ui.character.list

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.pekyurek.marvelcomics.R
import com.pekyurek.marvelcomics.databinding.FragmentCharacterListBinding
import com.pekyurek.marvelcomics.ui.adapter.CharacterAdapter
import com.pekyurek.marvelcomics.ui.adapter.RecyclerLoadStateAdapter
import com.pekyurek.marvelcomics.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, CharacterListViewModel>() {

    override val layoutResId: Int = R.layout.fragment_character_list

    override val viewModel: CharacterListViewModel by viewModels()

    private val characterAdapter by lazy {
        CharacterAdapter { navigatorExtra, character ->
            val action =
                CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                    character)
            findNavController().navigate(action, navigatorExtra)
        }
    }

    override fun onInit() {
        viewModel.loadPagingCharacterList()
    }

    override fun initViews() {
        initAnimations()
        binding.rvCharacterList.adapter = characterAdapter.withLoadStateFooter(
            RecyclerLoadStateAdapter(characterAdapter::retry)
        )

        lifecycleScope.launch {
            characterAdapter.loadStateFlow.collectLatest {
                val isLoading = it.refresh is LoadState.Loading
                viewModel.showLoading(isLoading)
                if (isLoading.not() && characterAdapter.itemCount != 0) cancel()
            }
        }
    }

    private fun initAnimations() {
        postponeEnterTransition()
        binding.rvCharacterList.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.pagingCharacterList.observe(this) {
            lifecycleScope.launch { characterAdapter.submitData(it) }
        }
    }
}