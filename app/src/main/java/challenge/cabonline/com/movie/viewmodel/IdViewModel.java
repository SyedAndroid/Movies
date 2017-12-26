package challenge.cabonline.com.movie.viewmodel;

import android.arch.lifecycle.ViewModel;

/**
 * Created by syed on 24/12/2017.
 */

public class IdViewModel extends ViewModel {

    private int movieId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
