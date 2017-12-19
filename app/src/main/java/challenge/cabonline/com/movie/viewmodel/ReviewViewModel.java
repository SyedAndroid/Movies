package challenge.cabonline.com.movie.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import challenge.cabonline.com.movie.model.Review;
import challenge.cabonline.com.movie.repository.RetrofitMovieService;


public class ReviewViewModel extends AndroidViewModel {

    private final LiveData<Review> reviewListObservable;

    public ReviewViewModel(@NonNull Application application, String movieId) {
        super(application);
        reviewListObservable = RetrofitMovieService.getInstance().getReviewList(movieId);
    }

    public LiveData<Review> getReviewListObservable() {
        return reviewListObservable;
    }
    /*public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String movieId;

        public Factory(@NonNull Application application, String movieId) {
            this.application = application;
            this.movieId = movieId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ReviewViewModel(application, movieId);
        }
    }*/


}
