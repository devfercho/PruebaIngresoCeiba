package co.com.ceiba.mobile.pruebadeingreso.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;



import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.Callback;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster;
import co.com.ceiba.mobile.pruebadeingreso.presenter.iCallback;

import static co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster.presenterMaster;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        showUser();
    }

    //Todo: Mostrara el recycler con los users
    private void showUser() {
        Log.i("showUser", "init");
        stopAsyncTask();
        if (presenterMaster == null) {
            presenterMaster = new PresenterMaster(this);
            presenterMaster.callback(new iCallback() {

                @Override
                public void getResult(Callback callback) {

                    //TODO Recibe el resultado y empieza hacer el recycler
                    mostrarUsuarios();
                    presenterMaster = null;
                }
            });
            presenterMaster.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }


    }

    private void mostrarUsuarios() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Arma el recycler y despliega los usuarios
            }
        });
    }

    private void stopAsyncTask() {
        if (presenterMaster != null) {
            presenterMaster.cancel(true);
            presenterMaster = null;
        }
    }



}