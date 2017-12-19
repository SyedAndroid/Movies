package challenge.cabonline.com.movie.model;

/**
 * Created by syed on 19/12/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<TrailerModel> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrailerModel> getTrailer() {
        return results;
    }

    public void setTrailer(List<TrailerModel> results) {
        this.results = results;
    }

}
