package challenge.cabonline.com.movie.database.dao;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import challenge.cabonline.com.movie.database.entity.MovieEntity;
import challenge.cabonline.com.movie.database.livedata.RealmLiveData;
import challenge.cabonline.com.movie.database.livedata.RealmResultsLiveData;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by syed on 06/12/2017.
 */

public class MovieDao extends Dao<MovieEntity> {

    @Inject
    public MovieDao(Realm db) {
        super(db);
    }

    public LiveData<List<MovieEntity>> findAllAsync() {
        return new RealmResultsLiveData<>(where().findAllAsync());
    }

    public LiveData<List<MovieEntity>> findByCategory(final String category) {
        return new RealmResultsLiveData<>(
                where()
                        .equalTo("category", category)
                        .findAllAsync());
    }

    public LiveData<MovieEntity> findByIdAsync(final int id) {
        return new RealmLiveData<>(
                where()
                        .equalTo("id", id)
                        .findFirstAsync());
    }

    private RealmQuery<MovieEntity> where() {
        return db.where(MovieEntity.class);
    }

    public void deleteAll() {
        db.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@Nonnull Realm bgRealm) {
                bgRealm.delete(MovieEntity.class);
            }
        });
    }
}