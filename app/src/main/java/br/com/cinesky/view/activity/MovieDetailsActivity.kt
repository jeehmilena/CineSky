package br.com.cinesky.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.cinesky.R
import br.com.cinesky.model.MovieResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setHomeButtonEnabled(true);

        val movie = intent.getSerializableExtra("movie") as MovieResult

        if (movie != null){
            Picasso.get().load(movie.cover_url).into(movieImageDetail)
            movieTitleDetails.text = movie.title
            movieDurationDetail.text = movie.duration
            movieReleaseYearDetail.text = movie.release_year
            movieOverview.text = movie.overview
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {android.R.id.home -> {
                startActivity( Intent(this, HomeActivity::class.java))
                finishActivity(0)
            }
        }
        return true
    }
}
