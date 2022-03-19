package com.example.musicplayerapp.adapter

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerapp.R
import com.example.musicplayerapp.model.MusicItem
import com.squareup.picasso.Picasso




class MusicAdapter(
    private val musics: MutableList<MusicItem> = mutableListOf(),
    //private val onFlowerClicked: (FlowersItem) -> Unit
) : RecyclerView.Adapter<MusicsViewHolder>() {





    fun updateMusics(newMusicss: List<MusicItem>) {
        musics.clear()
        musics.addAll(newMusicss)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicsViewHolder {
        val musicView = LayoutInflater.from(parent.context).inflate(R.layout.music_item, parent, false)
        return MusicsViewHolder(musicView)
    }

    override fun onBindViewHolder(holder: MusicsViewHolder, position: Int) {
        val musicItem = musics[position]
        holder.bind(musicItem)
    }

    override fun getItemCount(): Int = musics.size
}

class MusicsViewHolder(
    itemView: View
    //private val onFlowerClicked: (FlowersItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    val player = MediaPlayer()

    val musicCollection: TextView = itemView.findViewById(R.id.textViewAlbum)
    val musicDetail: TextView = itemView.findViewById(R.id.textViewDetail)
    val musicPrice: TextView = itemView.findViewById(R.id.textViewPrice)
    val musicPhoto: ImageView = itemView.findViewById(R.id.imageview)
    val cardView:CardView = itemView.findViewById(R.id.music_card)


    fun bind(music: MusicItem) {
        musicCollection.text = music.collectionName
        musicDetail.text = music.artistName
        musicPrice.text = music.collectionPrice.toString()

//        itemView.setOnClickListener {
//            onFlowerClicked.invoke(flower)
//        }

        cardView.setOnClickListener{




            Log.d("viewholder","card view clickee")
           //Toast.makeText()
             player.stop()
             player.reset()
             player.setDataSource(music.previewUrl)
             player.prepare()
             player.start()
        }

        Picasso.get()
            .load(music.artworkUrl30)
            .placeholder(R.drawable.images)
            .error(R.drawable.ic_launcher_background)
            .resize(250, 250)
            .into(musicPhoto)
    }
}