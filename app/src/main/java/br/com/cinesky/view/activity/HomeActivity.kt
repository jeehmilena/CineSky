package br.com.cinesky.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cinesky.R
import br.com.cinesky.model.MovieResult
import br.com.cinesky.view.adapter.MovieRecyclerViewAdapter
import br.com.cinesky.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val list = mutableListOf<MovieResult>()
        val adapter = MovieRecyclerViewAdapter(list)
        val viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        movieList.adapter = adapter
        movieList.layoutManager = GridLayoutManager(this, 2)

        viewModel.getAllMovies()

        viewModel.movieListResult.observe(this, Observer {
            adapter.updateList(it as MutableList<MovieResult>)
        })

        viewModel.loadingResult.observe(this, Observer {
            if (it){
                progressBar.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.GONE
            }
        })

    }
}
