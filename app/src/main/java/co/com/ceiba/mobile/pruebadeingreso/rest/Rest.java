package co.com.ceiba.mobile.pruebadeingreso.rest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.URL_BASE;

public class Rest {

    private Retrofit retrofit;

    public void getUserRest(final Activity myActivity) {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRest interfaceRest = retrofit.create(IRest.class);

        Call<List<User>> callUser = interfaceRest.getUsers();
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(myActivity.getBaseContext(), R.string.error_services, Toast.LENGTH_LONG);
                } else {
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
                Toast.makeText(myActivity.getBaseContext(), R.string.error_conexion, Toast.LENGTH_LONG);
            }
        });
    }

    public void getPostRest(final int idUser, final PostActivity postActivity, final ProgressDialog progressDialog) {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRest interfaceRest = retrofit.create(IRest.class);

        Call<List<Post>> callPost = interfaceRest.getPosts(idUser);
        callPost.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(postActivity.getBaseContext(), R.string.error_services, Toast.LENGTH_LONG);
                    progressDialog.dismiss();
                } else {
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
                Toast.makeText(postActivity.getBaseContext(), R.string.error_conexion, Toast.LENGTH_LONG);
                progressDialog.dismiss();
            }
        });
    }
}
