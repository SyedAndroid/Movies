package challenge.cabonline.com.movie.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.cabonline.com.movie.BuildConfig;
import challenge.cabonline.com.movie.R;
import challenge.cabonline.com.movie.ui.utils.Constants;
import challenge.cabonline.com.movie.viewmodel.IdViewModel;

/**
 * Created by syed on 18/12/2017.
 */

public class DetailActivity extends LifecycleActivity {
    @BindView(R.id.toolImage)
    ImageView toolImage;

    private IdViewModel idViewModel;

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
        idViewModel = ViewModelProviders.of(this).get(IdViewModel.class);
        idViewModel.setMovieId(movieId);


        String backdrop = getIntent().getStringExtra("backdrop");

        Picasso.with(this).load(Constants.IMAGE_URL + "/w500" + backdrop + "?api_key?=" + BuildConfig.API_KEY).into(toolImage);

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setMovieId(idViewModel.getMovieId());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.movie_detail_container, movieDetailFragment)
                .commit();
    }

}
