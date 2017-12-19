package challenge.cabonline.com.movie.database.util;


import android.content.Context;

import java.io.InputStream;

import challenge.cabonline.com.movie.database.entity.MovieEntity;
import io.realm.Realm;

public class DatabaseInitTransaction {
    String[] fileName = {"top", "popular", "now"};
    InputStream is;
    private Context context;

    public DatabaseInitTransaction(Context context) {
        this.context = context;
    }

    public void execute() {

        Realm db = Realm.getDefaultInstance();
        try {
            for (final String name : fileName) {

                db.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        int rID = context.getResources().getIdentifier("challenge.cabonline.com.movie:raw/" + name, null, null);
                        is = context.getResources().openRawResource(rID);
                        realm.createOrUpdateAllFromJson(MovieEntity.class, is);
                    }

                });
            }
        } finally {
            db.close();
        }







       /* List<MovieEntity> movieList = new ArrayList<>();
        for(int i =0;i<fileName.length;i++) {

            movieList = JSONUtils.loadMovies(context, fileName[i]);

            for (MovieEntity movieEntity : movieList) {
                movieDao.copyOrUpdate(movieEntity);
            }
        }*/
    }
}
