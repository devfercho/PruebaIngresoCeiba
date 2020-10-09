package co.com.ceiba.mobile.pruebadeingreso.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.ceiba.mobile.pruebadeingreso.database.dao.PostDao;
import co.com.ceiba.mobile.pruebadeingreso.database.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;

@Database(entities = {User.class, Post.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {


    public abstract UserDao userDao();

    public abstract PostDao postDao();

    public static AppDataBase getInstance(final Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "AppStore").build();
    }


}
