package challenge.cabonline.com.movie.ui.adapters;

/**
 * Created by syed on 26/12/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.cabonline.com.movie.R;
import challenge.cabonline.com.movie.model.MovieSearch;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MovieViewHolder> {

    // private ItemClickListener<? extends Recipe> clickListener;


    Activity activity;
    MovieSearch movie;
    private List<MovieSearch> movies;
    private MovieClickListener mMovieClickLister;

    public SearchAdapter(MovieClickListener mMovieClickLister, Activity activity) {
        this.mMovieClickLister = mMovieClickLister;
        this.activity = activity;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {

        movie = movies.get(position);

        String poster = "https://image.tmdb.org/t/p/w342" + movie.getPosterPath();
        Picasso.with(activity.getApplicationContext()).load(poster).into(holder.poster);
        holder.title.setText(movie.getTitle());

        holder.rating.setText(String.valueOf(movie.getVoteAverage()));

    }

    public void setMovieList(final List<MovieSearch> movieList) {
        movies = movieList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public interface MovieClickListener {

        void movieClickLister(MovieSearch movie, View view);

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.poster)
        ImageView poster;
        @BindView(R.id.movieTitle)
        TextView title;
        @BindView(R.id.rating)
        TextView rating;

        MovieViewHolder(View itemview) {
            super(itemview);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            mMovieClickLister.movieClickLister(movies.get(position), view);

        }


    }

}

