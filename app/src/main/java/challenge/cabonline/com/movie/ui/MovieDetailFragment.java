package challenge.cabonline.com.movie.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.cabonline.com.movie.BuildConfig;
import challenge.cabonline.com.movie.R;
import challenge.cabonline.com.movie.model.Movie;
import challenge.cabonline.com.movie.model.Review;
import challenge.cabonline.com.movie.model.Trailer;
import challenge.cabonline.com.movie.ui.adapters.ReviewAdapter;
import challenge.cabonline.com.movie.ui.adapters.TrailerAdapter;
import challenge.cabonline.com.movie.ui.utils.Constants;
import challenge.cabonline.com.movie.viewmodel.MovieIdViewModel;
import challenge.cabonline.com.movie.viewmodel.ReviewViewModel;
import challenge.cabonline.com.movie.viewmodel.TrailerViewModel;

/**
 * Created by syed on 14/12/2017.
 */

public class MovieDetailFragment extends LifecycleFragment {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.titleView)
    TextView titleView;
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.releaseText)
    TextView releaseText;
    @BindView(R.id.trailersRecyclerView)
    RecyclerView trailersRecyclerView;
    @BindView(R.id.reviewsRecyclerView)
    RecyclerView reviewsRecyclerView;
    @BindView(R.id.noReviewView)
    TextView noReviewView;
    @BindView(R.id.noTrailerView)
    TextView noTrailerView;
    @BindView(R.id.extras)
    LinearLayout extraLayout;
    ReviewAdapter reviewAdapter;
    TrailerAdapter trailerAdapter;
    int movieId;
    Activity activity;

    public MovieDetailFragment() {
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);
        ButterKnife.bind(this, rootView);


        MovieIdViewModel.Factory factory =
                new MovieIdViewModel.Factory(String.valueOf(movieId));

        MovieIdViewModel viewModel = ViewModelProviders.of(this, factory)
                .get(MovieIdViewModel.class);


        viewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {

                titleView.setText(movie.getTitle());

                Picasso.with(getContext()).load(Constants.IMAGE_URL + "/w342" + movie.getPoster() + "?api_key?=" + BuildConfig.API_KEY).fit().into(imageView);


                rating.setText(Double.toString(movie.getVoteAverage()).concat("/10"));
                ratingBar.setMax(5);
                ratingBar.setRating((float) movie.getVoteAverage() / 2f);

                overview.setText(movie.getOverview());
                releaseText.setText("Release Date: ".concat(movie.getReleaseDate()));
                movieId = movie.getId();
            }
        });

      /*  ReviewViewModel.Factory factory1 = new ReviewViewModel.Factory(
                getActivity().getApplication(),String.valueOf(movieId));*/

        final ReviewViewModel reviewViewModel = new ReviewViewModel(getActivity().getApplication(), String.valueOf(movieId));
        //   ViewModelProviders.of(this).get(ReviewViewModel.class);

        observeViewModel(reviewViewModel);

        TrailerViewModel.Factory factory2 = new TrailerViewModel.Factory(
                getActivity().getApplication(), String.valueOf(movieId));

        final TrailerViewModel trailerVewModel =
                ViewModelProviders.of(this, factory2).get(TrailerViewModel.class);

        observeTrailerViewModel(trailerVewModel);


        return rootView;
    }


    private void observeTrailerViewModel(final TrailerViewModel trailerVewModel) {
        trailerVewModel.getTrailerListObservable().observe(this, new Observer<Trailer>() {
            @Override
            public void onChanged(@Nullable Trailer trailers) {
                if (trailers.getTrailer() != null) {
                    trailerAdapter = new TrailerAdapter(getContext(), trailers.getTrailer());
                    trailerAdapter.addAll(trailers.getTrailer());
                    LinearLayoutManager trailerLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    trailersRecyclerView.setLayoutManager(trailerLayoutManager);
                    trailersRecyclerView.setAdapter(trailerAdapter);

                }
                if (trailerAdapter.getData().isEmpty()) {
                    trailersRecyclerView.setVisibility(View.INVISIBLE);
                    noTrailerView.setVisibility(View.VISIBLE);
                }
            }

        });

    }


    private void observeViewModel(ReviewViewModel viewModel) {


        viewModel.getReviewListObservable().observe(this, new Observer<Review>() {
            @Override
            public void onChanged(@Nullable Review reviews) {
                if (reviews.getResults() != null) {
                    LinearLayoutManager reviewLayoutManager = new LinearLayoutManager(getContext());
                    reviewAdapter = new ReviewAdapter(getContext(), reviews.getResults());
                    reviewsRecyclerView.setLayoutManager(reviewLayoutManager);
                    reviewAdapter.addAll(reviews.getResults());
                    reviewsRecyclerView.setAdapter(reviewAdapter);
                    if (reviewAdapter.getData().isEmpty()) {
                        reviewsRecyclerView.setVisibility(View.INVISIBLE);
                        noReviewView.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
    }

}