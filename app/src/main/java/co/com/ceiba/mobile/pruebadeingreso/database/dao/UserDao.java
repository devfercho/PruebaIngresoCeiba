package co.com.ceiba.mobile.pruebadeingreso.database.dao;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE idUser IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE name LIKE :first AND " +
            "name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(List<User> users);

    @Delete
    void delete(User user);
}
