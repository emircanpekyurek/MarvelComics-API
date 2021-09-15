package com.pekyurek.marvelcomics.ui.adapter.comarator

import androidx.recyclerview.widget.DiffUtil
import com.pekyurek.marvelcomics.domain.model.character.Character

class CharacterComparator : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}