package challenge.cabonline.com.movie.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.cabonline.com.movie.BuildConfig;
import challenge.cabonline.com.movie.R;
import challenge.cabonline.com.movie.ui.utils.Constants;

/**
 * Created by syed on 18/12/2017.
 */

public class DetailActivity extends LifecycleActivity {
    @BindView(R.id.toolImage)
    ImageView toolImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setActionBar(toolbar);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setTitle("");
        }
        ButterKnife.bind(this);

        int movieId = getIntent().getIntExtra("movieId", 0);
        String backdrop = getIntent().getStringExtra("backdrop");

        Glide.with(this).load(Constants.IMAGE_URL + "/w500" + backdrop + "?api_key?=" + BuildConfig.API_KEY).into(toolImage);

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setMovieId(movieId);
        supportPostponeEnterTransition();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.movie_detail_container, movieDetailFragment)
                .commit();
        supportStartPostponedEnterTransition();
    }

}
