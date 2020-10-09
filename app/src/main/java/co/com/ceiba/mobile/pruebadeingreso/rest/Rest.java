package co.com.ceiba.mobile.pruebadeingreso.rest;

import android.content.Context;
import android.util.Log;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.URL_BASE;

public class Rest {

    private Retrofit retrofit;

    public void getUserRest(final Context context){

         retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iRest interfaceRest = retrofit.create(iRest.class);

        Call<List<User>> callUser = interfaceRest.getUsers();

        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    //TODO: validar si es diferente a 200
                }else{
                    Log.i("onResponse", "else");
                    List<User> users = response.body();
                    PresenterMaster.getInstance(context).insertUser(users);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                //TODO: mostrar que fallo al traer los datos
            }
        });

    }
}
