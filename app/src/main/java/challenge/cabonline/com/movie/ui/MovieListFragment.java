package challenge.cabonline.com.movie.ui;


import android.animation.Animator;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import java.util.List;

import challenge.cabonline.com.movie.R;
import challenge.cabonline.com.movie.model.Movie;
import challenge.cabonline.com.movie.ui.adapters.MovieAdapter;
import challenge.cabonline.com.movie.viewmodel.MovieViewModel;

public class MovieListFragment extends LifecycleFragment implements MovieAdapter.MovieClickListener {
    public static final String ARG_PAGE = "ARG_PAGE";
    String category;
    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;


    public static MovieListFragment newInstance(int page, String category) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MovieListFragment fragment = new MovieListFragment();
        fragment.setCategory(category);
        fragment.setArguments(args);
        return fragment;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = LayoutInflater.from(getContext())
                .inflate(R.layout.movie_list_fragment, container, false);

        movieAdapter = new MovieAdapter(getActivity(),this);
        RecyclerView recyclerView = root.findViewById(R.id.movies_list_recycler_view);
        recyclerView.setAdapter(movieAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MovieViewModel.Factory factory =
                new MovieViewModel.Factory(category);

        movieViewModel = ViewModelProviders.of(this, factory).get(MovieViewModel.class);

        movieViewModel.getMovies().observe(this, new Observer<List<? extends Movie>>() {
            @Override
            public void onChanged(@Nullable List<? extends Movie> movies) {
                movieAdapter.setMovieList(movies);
            }
        });


        //  initializeStreamButton();
    }

    @Override
    public void movieClickLister(Movie movie ,View view) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("movieId", movie.getId() );
        intent.putExtra("backdrop",movie.getBackground());
                /*ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, holder.poster, "picture");*/
        int finalRadius = (int)Math.hypot(view.getWidth()/2, view.getHeight()/2);
        Animator anim = ViewAnimationUtils.createCircularReveal(view, (int) view.getWidth()/2, (int) view.getHeight()/2, 0, finalRadius);
        anim.start();
        startActivity(intent);
        getActivity().overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up  );
    }
}
