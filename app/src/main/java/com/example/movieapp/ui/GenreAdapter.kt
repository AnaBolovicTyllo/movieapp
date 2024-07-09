package com.example.movieapp.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.GenreItemBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private val genres: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int = genres.size

    @SuppressLint("NotifyDataSetChanged")
    fun add(newItems: List<String>) {
        val startPosition = genres.size
        genres.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
        Log.d("New genres", genres.toString())
    }

    inner class GenreViewHolder(private val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: String) {
            with(binding) {
                Log.d("Binding", genre)
                tvGenre.text = genre
            }
        }
    }
}
