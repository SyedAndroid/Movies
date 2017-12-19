package challenge.cabonline.com.movie.model;

/**
 * Created by syed on 06/12/2017.
 */

public interface Movie {
    int getId();

    String getTitle();

    String getOverview();

    String getPoster();

    double getVoteAverage();

    String getBackground();

    String getReleaseDate();
}
