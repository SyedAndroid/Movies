package challenge.cabonline.com.movie.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.util.List;

import challenge.cabonline.com.movie.database.dao.MovieDao;
import challenge.cabonline.com.movie.database.entity.MovieEntity;
import challenge.cabonline.com.movie.model.Movie;
import io.realm.Realm;

/**
 * Created by syed on 06/12/2017.
 */

public class MovieViewModel extends ViewModel {

    private final Realm database;
    private final MovieDao dao;

    private LiveData<List<? extends Movie>> movies;

    public MovieViewModel(String category) {
        database = Realm.getDefaultInstance();
        dao = new MovieDao(database);
        movies = Transformations.map(dao.findByCategory(category),
                new Function<List<MovieEntity>, List<? extends Movie>>() {
                    @Override
                    public List<? extends Movie> apply(List<MovieEntity> input) {
                        return input;
                    }
                });

    }

    public LiveData<List<? extends Movie>> getMovies() {
        return movies;
    }

    @Override
    protected void onCleared() {
        database.close();
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final String category;

        public Factory(String category) {
            this.category = category;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MovieViewModel(category);
        }
    }
}
