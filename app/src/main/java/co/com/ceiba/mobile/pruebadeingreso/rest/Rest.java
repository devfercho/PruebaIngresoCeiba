package co.com.ceiba.mobile.pruebadeingreso.rest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster;
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;
import io.realm.Progress;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_POST_USER;
import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.URL_BASE;

public class Rest {

    private Retrofit retrofit;

    public void getUserRest(final Activity myActivity) {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iRest interfaceRest = retrofit.create(iRest.class);

        Call<List<User>> callUser = interfaceRest.getUsers();
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (!response.isSuccessful()) {
                    //TODO: validar si es diferente a 200

                } else {
                    Log.i("onResponse", "else");
                    final List<User> users = response.body();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            PresenterMaster.getInstance(myActivity).insertUser(users);
                            PresenterMaster.getInstance(myActivity).mostrarUsuarios(users);

                        }
                    }).start();

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                //TODO: mostrar que fallo al traer los datos

            }
        });
    }

    public void getPostRest(final int idUser, final PostActivity postActivity, final ProgressDialog progressDialog) {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iRest interfaceRest = retrofit.create(iRest.class);

        Call<List<Post>> callPost = interfaceRest.getPosts(idUser);
        callPost.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    //TODO: validar si es diferente a 200
                    progressDialog.dismiss();
                } else {
                    Log.i("onResponse", "else");
                    final List<Post> posts = response.body();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            PresenterMaster.getInstance(postActivity).insertPost(posts);
                            PresenterMaster.getInstance(postActivity).mostrarPosts(posts, postActivity, progressDialog);

                        }
                    }).start();

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //TODO: mostrar que fallo al traer los datos
                progressDialog.dismiss();
            }
        });
    }
}
