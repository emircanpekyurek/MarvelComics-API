package com.pekyurek.marvelcomics.ui.character.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.pekyurek.marvelcomics.R
import com.pekyurek.marvelcomics.databinding.FragmentCharacterDetailBinding
import com.pekyurek.marvelcomics.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>() {

    override val layoutResId: Int = R.layout.fragment_character_detail

    override val viewModel: CharacterDetailViewModel by viewModels()

    private val args: CharacterDetailFragmentArgs by navArgs()

    private val character get() = args.argCharacterDetail

    override fun initBinding() {
        binding.character = character
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun initViews() {
        initAnimation()
    }

    private fun initAnimation() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onInit() {
        character.id?.let { viewModel.loadCharacterComics(it) }
    }

}