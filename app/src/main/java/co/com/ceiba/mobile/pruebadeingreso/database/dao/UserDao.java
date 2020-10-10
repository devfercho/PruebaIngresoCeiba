package co.com.ceiba.mobile.pruebadeingreso.database.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insertAll(List<User> users);

}
