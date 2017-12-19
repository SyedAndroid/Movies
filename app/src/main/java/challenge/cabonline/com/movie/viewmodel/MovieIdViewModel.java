package challenge.cabonline.com.movie.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import challenge.cabonline.com.movie.database.dao.MovieDao;
import challenge.cabonline.com.movie.database.entity.MovieEntity;
import challenge.cabonline.com.movie.model.Movie;
import io.realm.Realm;

/**
 * Created by syed on 14/12/2017.
 */

public class MovieIdViewModel extends ViewModel {

    private Realm database;
    private MovieDao dao;

    private LiveData<Movie> movie;

    public MovieIdViewModel(String movieId) {
        database = Realm.getDefaultInstance();
        dao = new MovieDao(database);

        movie = Transformations.map(dao.findByIdAsync(Integer.valueOf(movieId)),
                new Function<MovieEntity, Movie>() {
                    @Override
                    public Movie apply(MovieEntity input) {
                        return input;
                    }
                });


    }

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public void deleteAllMoviess() {
        dao.deleteAll();
    }

    @Override
    protected void onCleared() {
        database.close();
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final String movieId;

        public Factory(String movieId) {
            this.movieId = movieId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MovieIdViewModel(movieId);
        }
    }
}




