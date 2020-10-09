package co.com.ceiba.mobile.pruebadeingreso.database.dao;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.Post;

@Dao
public interface PostDao {

    @Query("SELECT * FROM post")
    List<Post> getAll();

    @Query("SELECT * FROM post WHERE userId IN (:userId)")
    List<Post> loadAllByIds(int[] userId);

    @Insert
    void insertAll(List<Post> posts);

}
