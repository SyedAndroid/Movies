package challenge.cabonline.com.movie.repository;

import challenge.cabonline.com.movie.model.Review;
import challenge.cabonline.com.movie.model.Trailer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by syed on 18/12/2017.
 */

public interface MovieService {

    @GET("/3/movie/{id}/videos")
    Call<Trailer> loadTrailers(@Path("id") String id, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/reviews")
    Call<Review> loadReviews(@Path("id") String id, @Query("api_key") String api_key);


}

