package br.com.wtecsuprimentos.device.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import br.com.wtecsuprimentos.device.data.dao.CustomerDao;
import br.com.wtecsuprimentos.device.data.models.CustomerModel;

@Database(entities = {CustomerModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = "DABUEK";
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "wtecdatabase";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract CustomerDao customerDao();
}
