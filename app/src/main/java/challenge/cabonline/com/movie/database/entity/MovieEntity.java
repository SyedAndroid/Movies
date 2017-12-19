package challenge.cabonline.com.movie.database.entity;

import challenge.cabonline.com.movie.model.Movie;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by syed on 06/12/2017.
 */
@RealmClass
public class MovieEntity implements RealmModel, Movie {
    @PrimaryKey
    int id;
    double vote_average;
    String title;
    String poster_path;
    String overview;
    String category;
    String backdrop_path;
    String release_date;

    public MovieEntity(int id, String title, String poster_path, String overview, double vote_average) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_average = vote_average;
    }

    public MovieEntity() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String getPoster() {
        return poster_path;
    }

    @Override
    public double getVoteAverage() {
        return vote_average;
    }

    @Override
    public String getBackground() {
        return backdrop_path;
    }

    @Override
    public String getReleaseDate() {
        return release_date;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
