package com.example.movieapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MoviePopularItemBinding
import com.example.movieapp.models.MovieUi
import com.squareup.picasso.Picasso

class MovieAdapter(): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val popularMovies: MutableList<MovieUi> = mutableListOf()
    private var onClickListener: IMovieOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MoviePopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(popularMovies[position])
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    fun add(newItems: List<MovieUi>) {
        val startPosition = popularMovies.size
        popularMovies.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    fun setOnClickListener(listener: IMovieOnClickListener){
        this.onClickListener = listener
    }

    inner class MovieViewHolder(private val binding: MoviePopularItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieUi) {
            val posterBaseURL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"
            with(binding) {
                Picasso.get()
                    .load(posterBaseURL+movie.posterPath)
                    .into(ivMovieIcon)
                tvCaption.text = movie.title
                tvRate.text = (movie.voteAverage).toString()
                root.setOnClickListener{
                    onClickListener?.onClick(movie)
                }
            }
        }
    }

}

interface IMovieOnClickListener{
    fun onClick(movie: MovieUi)
}
abstract class EndlessRecyclerViewScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(view, dx, dy)
        val visibleItemCount = view.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}

