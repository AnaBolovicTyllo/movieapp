package com.example.movieapp.ui

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ImageItemBinding
import com.example.movieapp.databinding.VideoItemBinding
import com.google.android.exoplayer2.SimpleExoPlayer
import com.squareup.picasso.Picasso


class MediaAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mediaItems: MutableList<MediaItem> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MediaItem.TYPE_VIDEO) {
            val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            VideoViewHolder(binding)
        } else {
            val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ImageViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mediaItems[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mediaItems[position]
        if (holder is VideoViewHolder) {
            holder.bind(item.url)
        } else if (holder is ImageViewHolder) {
            holder.bind(item.url)
        }
    }

    fun add(newItems: List<MediaItem>) {
        val startPosition = mediaItems.size
        mediaItems.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    override fun getItemCount(): Int {
        return mediaItems.size
    }

    inner class VideoViewHolder(private val binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var player: SimpleExoPlayer = SimpleExoPlayer.Builder(binding.root.context).build()
        init {
            binding.playerView.player = player
        }

        fun bind(url: String) {
            val mediaItem = com.google.android.exoplayer2.MediaItem.Builder()
                .setUri(Uri.parse(url))
                .build()
            player.setMediaItem(mediaItem)
            player.prepare()
        }

        fun releasePlayer() {
            player.release()
        }
    }

    inner class ImageViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            Picasso.get().load(url).into(binding.imageView)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if (holder is VideoViewHolder) {
            holder.releasePlayer()
        }
    }
}


data class MediaItem(
    val type: Int,
    val url: String
) {
    companion object {
        const val TYPE_VIDEO = 0
        const val TYPE_IMAGE = 1
    }
}