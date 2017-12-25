package challenge.cabonline.com.movie.ui.adapters;

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
import challenge.cabonline.com.movie.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    // private ItemClickListener<? extends Recipe> clickListener;

    Activity activity;

    private List<? extends Movie> movies;

    private MovieClickListener mMovieClickLister;
     Movie movie;

    public MovieAdapter( Activity activity,MovieClickListener mMovieClickLister) {
        this.activity = activity;
        this.mMovieClickLister = mMovieClickLister;
    }

    public void setMovieList(final List<? extends Movie> movieList) {
        movies = movieList;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    public interface MovieClickListener {

        void movieClickLister(Movie movie, View view);

    }
    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {

         movie = movies.get(position);

        String poster = "https://image.tmdb.org/t/p/w342" + movie.getPoster();
        Picasso.with(activity.getApplicationContext()).load(poster).into(holder.poster);
        holder.title.setText(movie.getTitle());

        holder.rating.setText(String.valueOf(movie.getVoteAverage()));

    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder   implements View.OnClickListener {

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

            mMovieClickLister.movieClickLister(movies.get(position),view);

        }


    }

}
