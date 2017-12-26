package challenge.cabonline.com.movie.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import challenge.cabonline.com.movie.BuildConfig;
import challenge.cabonline.com.movie.model.Review;
import challenge.cabonline.com.movie.model.SearchResult;
import challenge.cabonline.com.movie.model.Trailer;
import challenge.cabonline.com.movie.ui.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by syed on 18/12/2017.
 */

public class RetrofitMovieService {

    private static RetrofitMovieService retrofitMovieService;
    private MovieService movieService;

    private RetrofitMovieService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieService = retrofit.create(MovieService.class);
    }

    public static RetrofitMovieService getInstance() {

        if (retrofitMovieService == null) {
            retrofitMovieService = new RetrofitMovieService();
        }
        return retrofitMovieService;
    }

    public LiveData<Review> getReviewList(String movieId) {
        final MutableLiveData<Review> data = new MutableLiveData<>();

        movieService.loadReviews(movieId, BuildConfig.API_KEY).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Trailer> getTrailerList(String movieId) {
        final MutableLiveData<Trailer> data = new MutableLiveData<>();

        movieService.loadTrailers(movieId, BuildConfig.API_KEY).enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<SearchResult> getSearchResults(String search) {
        final MutableLiveData<SearchResult> data = new MutableLiveData<>();

        movieService.loadSearch(search, BuildConfig.API_KEY).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }


    public MovieService getMovieService() {
        return movieService;
    }
}

