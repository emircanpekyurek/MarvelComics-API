package com.pekyurek.marvelcomics.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pekyurek.marvelcomics.R
import com.pekyurek.marvelcomics.databinding.ItemLoadStateBinding
import com.pekyurek.marvelcomics.presentation.core.extensions.inflateDataBinding

class RecyclerLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<RecyclerLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        return LoadStateViewHolder(parent.inflateDataBinding(R.layout.item_load_state))
    }

    inner class LoadStateViewHolder(private val binding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.loadState = loadState
            binding.btnErrorState.setOnClickListener { retry() }
            binding.executePendingBindings()
        }
    }
}