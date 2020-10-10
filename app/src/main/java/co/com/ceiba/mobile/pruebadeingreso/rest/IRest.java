package co.com.ceiba.mobile.pruebadeingreso.rest;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_POST_USER;
import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_USERS;

public interface IRest {

    @GET(GET_USERS)
    Call<List<User>> getUsers();

    @GET(GET_POST_USER)
    Call<List<Post>> getPosts(@Query("userId") int userId);
}
