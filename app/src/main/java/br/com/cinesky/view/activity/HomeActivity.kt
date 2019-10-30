package br.com.cinesky.view.activity

import android.content.Intent
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
import br.com.cinesky.view.interfaces.OnClick
import br.com.cinesky.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), OnClick{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val list = mutableListOf<MovieResult>()
        val adapter = MovieRecyclerViewAdapter(list, this)
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

        viewModel.errorResult.observe(this, Observer {
            Snackbar.make(movieList, it, Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun clickMovie(movieResult: MovieResult) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movieResult)
        startActivity(intent)

        Snackbar.make(movieList, movieResult.title, Snackbar.LENGTH_SHORT).show()
    }

}
