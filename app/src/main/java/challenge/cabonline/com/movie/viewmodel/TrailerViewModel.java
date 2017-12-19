package challenge.cabonline.com.movie.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import challenge.cabonline.com.movie.model.Trailer;
import challenge.cabonline.com.movie.repository.RetrofitMovieService;

/**
 * Created by syed on 19/12/2017.
 */

public class TrailerViewModel extends AndroidViewModel {

    private final LiveData<Trailer> trailerListObservable;

    public TrailerViewModel(@NonNull Application application, String movieId) {
        super(application);
        trailerListObservable = RetrofitMovieService.getInstance().getTrailerList(movieId);
    }

    public LiveData<Trailer> getTrailerListObservable() {
        return trailerListObservable;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String movidId;

        public Factory(@NonNull Application application, String movidId) {
            this.application = application;
            this.movidId = movidId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TrailerViewModel(application, movidId);
        }
    }


}
