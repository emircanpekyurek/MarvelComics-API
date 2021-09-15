package com.pekyurek.marvelcomics.ui.adapter

import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pekyurek.marvelcomics.R
import com.pekyurek.marvelcomics.databinding.ItemCharacterBinding
import com.pekyurek.marvelcomics.domain.model.character.Character
import com.pekyurek.marvelcomics.presentation.core.extensions.inflateDataBinding
import com.pekyurek.marvelcomics.ui.adapter.comarator.CharacterComparator

class CharacterAdapter(
    private val onItemClick: (navigationExtra: FragmentNavigator.Extras, character: Character) -> Unit
) : PagingDataAdapter<Character, CharacterAdapter.ViewHolder>(CharacterComparator()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateDataBinding(R.layout.item_character))
    }

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.character = character
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onItemClick.invoke(
                    FragmentNavigatorExtras(binding.ivCharacterIcon to binding.ivCharacterIcon.transitionName),
                    character
                )
            }
        }
    }

}