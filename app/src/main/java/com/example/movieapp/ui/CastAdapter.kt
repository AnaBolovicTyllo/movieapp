package com.example.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Constants
import com.example.movieapp.databinding.MoviePopularItemBinding
import com.example.movieapp.models.ActorDao
import com.squareup.picasso.Picasso

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private val cast: MutableList<ActorDao> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.CastViewHolder {
        val binding = MoviePopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastAdapter.CastViewHolder, position: Int) {
        val layoutParams = holder.itemView.layoutParams
        layoutParams.width = 300
        val cast = cast[position]
        holder.bind(cast)
    }

    override fun getItemCount(): Int {
        return cast.size
    }

    fun add(newItems: List<ActorDao>) {
        val startPosition = cast.size
        cast.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    inner class CastViewHolder(private val binding: MoviePopularItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: ActorDao) {
            with(binding) {
                Picasso.get()
                    .load(Constants.POSTER_BASE_URL+actor.profile_path)
                    .into(ivMovieIcon)
                tvCaption.text = actor.name
                tvRate.text = actor.character
            }
        }
    }
}