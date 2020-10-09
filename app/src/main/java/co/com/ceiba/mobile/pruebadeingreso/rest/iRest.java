package co.com.ceiba.mobile.pruebadeingreso.rest;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import retrofit2.Call;
import retrofit2.http.GET;

import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_POSTS;
import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_USERS;

public interface iRest {

    @GET(GET_USERS)
    Call<List<User>> getUsers();

    @GET(GET_POSTS)
    Call<List<Post>> getPosts();
}
