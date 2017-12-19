package challenge.cabonline.com.movie.database.util;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import challenge.cabonline.com.movie.database.entity.MovieEntity;
import challenge.cabonline.com.movie.model.Review;
import challenge.cabonline.com.movie.model.Trailer;

public class JSONUtils {

    private static final String TAG = "Utils";

    public static List<MovieEntity> loadMovies(Context context, String fileName) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(fileName, context));
            List<MovieEntity> moviesList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                MovieEntity movie = gson.fromJson(array.getString(i), MovieEntity.class);
                movie.setCategory(fileName);
                moviesList.add(movie);
            }
            return moviesList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(String jsonFileName, Context context) {
        String json = null;
        InputStream is = null;
        try {
            int rID = context.getResources().getIdentifier("challenge.cabonline.com.movie:raw/" + jsonFileName, null, null);
            Log.d(TAG, "path " + jsonFileName);
            is = context.getResources().openRawResource(rID);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static List<Review> loadReviews(String JSONstring) {
        List<Review> reviewList = new ArrayList<>();

        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            JSONObject jsonObject = new JSONObject(JSONstring);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject sourceObject = jsonArray.getJSONObject(i);

                Review review = gson.fromJson(jsonArray.getString(i), Review.class);
                reviewList.add(review);
            }
            return reviewList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Trailer> loadTrailers(String JSONstring) {
        List<Trailer> trailerList = new ArrayList<>();

        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            JSONObject jsonObject = new JSONObject(JSONstring);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {

                Trailer trailer = gson.fromJson(jsonArray.getString(i), Trailer.class);
                trailerList.add(trailer);
            }
            return trailerList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
