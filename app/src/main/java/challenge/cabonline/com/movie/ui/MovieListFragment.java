package challenge.cabonline.com.movie.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import challenge.cabonline.com.movie.R;
import challenge.cabonline.com.movie.model.Movie;
import challenge.cabonline.com.movie.ui.adapters.MovieAdapter;
import challenge.cabonline.com.movie.viewmodel.MovieViewModel;

public class MovieListFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = LayoutInflater.from(getContext())
                .inflate(R.layout.movie_list_fragment, container, false);

        movieAdapter = new MovieAdapter(getContext(), getActivity());
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

    /*private void initializeStreamButton() {
        streamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingActionButton fab = (FloatingActionButton) v;
                if(fab.isSelected()) {
                    fab.setSelected(false);
                    movieListViewModel.stopStreaming();
                } else {
                    fab.setSelected(true);
                    movieListViewModel.startStreaming();
                }
            }
        });
        streamButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                movieListViewModel.deleteAllMoviess();
                return true;
            }
        });*/


}
