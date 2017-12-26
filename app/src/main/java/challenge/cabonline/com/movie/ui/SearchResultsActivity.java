package challenge.cabonline.com.movie.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.cabonline.com.movie.BuildConfig;
import challenge.cabonline.com.movie.R;
import challenge.cabonline.com.movie.model.MovieSearch;
import challenge.cabonline.com.movie.model.SearchResult;
import challenge.cabonline.com.movie.repository.RetrofitMovieService;
import challenge.cabonline.com.movie.ui.adapters.SearchAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsActivity extends LifecycleActivity implements SearchAdapter.MovieClickListener {
    @BindView(R.id.search_query)
    EditText searchQuery;
    @BindView(R.id.movies_list_recycler_view)
    RecyclerView list;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        setActionBar(toolbar);
        setTitle("Search");
        searchAdapter = new SearchAdapter(this, this);
        list.setLayoutManager(new LinearLayoutManager(this));

        searchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                updateSearch(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    public void updateSearch(String query) {
        if (!TextUtils.equals(query, "")) {
            RetrofitMovieService.getInstance().getMovieService().loadSearch(query, BuildConfig.API_KEY).enqueue(new Callback<SearchResult>() {
                @Override
                public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                    searchAdapter.setMovieList(response.body().getResults());
                    list.setAdapter(searchAdapter);
                }

                @Override
                public void onFailure(Call<SearchResult> call, Throwable t) {

                }
            });
        }

    }


    @Override
    public void movieClickLister(MovieSearch movie, View view) {

    }
}
