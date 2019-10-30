package br.com.cinesky.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cinesky.R
import br.com.cinesky.model.MovieResult
import br.com.cinesky.view.interfaces.OnClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_recyclerview.view.*

class MovieRecyclerViewAdapter(var movieList: MutableList<MovieResult>, val listener: OnClick) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_recyclerview, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList.get(position)
        holder.onBind(movie)

        holder.itemView.setOnClickListener {
            listener.clickMovie(movie)
        }
    }

    fun updateList(newList: MutableList<MovieResult>) {
        this.movieList.removeAll(movieList)
        this.movieList = newList
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(movie: MovieResult) {
            itemView.movieName.text = movie.title
            Picasso.get().load(movie.cover_url).into(itemView.movieImage)
        }

    }
}