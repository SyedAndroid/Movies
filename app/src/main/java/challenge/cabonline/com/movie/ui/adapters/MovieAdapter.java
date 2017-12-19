package challenge.cabonline.com.movie.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
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
import challenge.cabonline.com.movie.ui.DetailActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    // private ItemClickListener<? extends Recipe> clickListener;
    Context context;
    Activity activity;
    private List<? extends Movie> movies;

    public MovieAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
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

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {

        final Movie movie = movies.get(position);
        String poster = "https://image.tmdb.org/t/p/w342" + movie.getPoster();
        Picasso.with(context).load(poster).into(holder.poster);
        holder.title.setText(movie.getTitle());

        holder.rating.setText(String.valueOf(movie.getVoteAverage()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movieId", movie.getId());
                intent.putExtra("backdrop", movie.getBackground());
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, holder.poster, "picture");
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.poster)
        ImageView poster;
        @BindView(R.id.movieTitle)
        TextView title;
        @BindView(R.id.rating)
        TextView rating;

        MovieViewHolder(View itemview) {
            super(itemview);
            ButterKnife.bind(this, itemView);
        }
    }
}
