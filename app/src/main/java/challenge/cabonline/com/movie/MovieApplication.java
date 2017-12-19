package challenge.cabonline.com.movie;

import android.app.Application;

import challenge.cabonline.com.movie.database.util.DatabaseInitTransaction;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseInitTransaction initializeData = new DatabaseInitTransaction(this);
        Realm.init(getApplicationContext());

        Realm.setDefaultConfiguration(
                new RealmConfiguration.Builder()
                        .schemaVersion(1)
                        .deleteRealmIfMigrationNeeded()
                        .build());
        initializeData.execute();

    }

}
