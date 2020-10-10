package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.Callback;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster;
import co.com.ceiba.mobile.pruebadeingreso.presenter.iCallback;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UserAdapter;

import static co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster.presenterMaster;
import static co.com.ceiba.mobile.pruebadeingreso.presenter.Status.OK;
import static co.com.ceiba.mobile.pruebadeingreso.presenter.Status.REST;

public class MainActivity extends AppCompatActivity {

    private List<User> users;
    private RecyclerView recyclerUser;
    private UserAdapter userAdapter;
    private EditText etName;
    private TextView tvEmptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerUser = findViewById(R.id.recyclerViewSearchResults);
        etName = findViewById(R.id.editTextSearch);
        tvEmptyList = findViewById(R.id.tvEmptyList);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    @Override
    protected void onResume() {
        //TODO hacer que se limpie el editText, luego de volver de la activity de post
        Log.i("f", "onResume");
        super.onResume();
    }

    private void filter(String textSearch) {
        List<User> filterUser = new ArrayList<>();

        if (users != null && users.isEmpty())
            users = userAdapter.getListUser();
        for (User user : users) {
            if (user.getName().toLowerCase().contains(textSearch.toLowerCase())) {
                filterUser.add(user);
            }
        }
        if (filterUser.isEmpty()) {
            recyclerUser.setVisibility(View.INVISIBLE);
            tvEmptyList.setVisibility(View.VISIBLE);
        } else{
            tvEmptyList.setVisibility(View.INVISIBLE);
            recyclerUser.setVisibility(View.VISIBLE);
            userAdapter.filterList(filterUser);
        }

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
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando Usuarios...");
        progressDialog.cancel();
        progressDialog.show();
        if (presenterMaster == null) {
            presenterMaster = new PresenterMaster(MainActivity.this);
            presenterMaster.callback(new iCallback() {
                @Override
                public void getResult(Callback callback) {
                    if (callback.getResult() == OK) {
                        //TODO Recibe el resultado y empieza hacer el recycler
                        MainActivity.this.setUsers(callback.getUser());
                        mostrarUsuarios(MainActivity.this.users);
                        progressDialog.dismiss();
                    } else if (callback.getResult() == REST) {
                        progressDialog.dismiss();
                    }
                    presenterMaster = null;
                }
            });
            presenterMaster.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private void setUsers(List<User> users) {
        this.users = users;
    }

    public void mostrarUsuarios(final List<User> users) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Arma el recycler y despliega los usuarios
                userAdapter = new UserAdapter(users, MainActivity.this.getApplicationContext());
                recyclerUser.setLayoutManager(new LinearLayoutManager(MainActivity.this.getApplicationContext()));
                recyclerUser.setAdapter(userAdapter);
            }
        });
    }

    private void stopAsyncTask() {
        if (presenterMaster != null) {
            presenterMaster.cancel(true);
            presenterMaster = null;
        }
    }


    public void openPosts() {

    }

}