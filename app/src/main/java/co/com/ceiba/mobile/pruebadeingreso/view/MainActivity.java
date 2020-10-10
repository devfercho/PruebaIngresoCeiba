package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.Callback;
import co.com.ceiba.mobile.pruebadeingreso.presenter.ICallback;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UserAdapter;

import static co.com.ceiba.mobile.pruebadeingreso.presenter.Callback.OK;
import static co.com.ceiba.mobile.pruebadeingreso.presenter.Callback.REST;

public class MainActivity extends AppCompatActivity {

    private List<User> users;
    private RecyclerView recyclerUser;
    private UserAdapter userAdapter;
    private TextView tvEmptyList;
    private EditText etName;


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
                //No usado
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //No usado
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


    }

    //TODO hacer que se limpie el editText, luego de volver de la activity de post


    private void filter(String textSearch) {
        if(!textSearch.isEmpty()){
            List<User> filterUser = new ArrayList<>();

            if (users == null || (users != null && users.isEmpty())) {
                users = userAdapter.getListUser();
            }


            for (User user : users) {
                if (user.getName().toLowerCase().contains(textSearch.toLowerCase())) {
                    filterUser.add(user);
                }
            }
            if (filterUser.isEmpty()) {
                recyclerUser.setVisibility(View.INVISIBLE);
                tvEmptyList.setVisibility(View.VISIBLE);
            } else {
                tvEmptyList.setVisibility(View.INVISIBLE);
                recyclerUser.setVisibility(View.VISIBLE);
                userAdapter.filterList(filterUser);
            }
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        etName.setText("");
        showUser();
    }

    private void showUser() {

        stopAsyncTask();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.load_user));
        progressDialog.cancel();
        progressDialog.show();
        PresenterMaster.getInstance(this).callback(new ICallback() {
            @Override
            public void getResult(Callback callback) {
                if (callback.getResult() == OK) {
                    MainActivity.this.setUsers(callback.getUser());
                    mostrarUsuarios(MainActivity.this.users);
                    progressDialog.dismiss();
                } else if (callback.getResult() == REST) {
                    progressDialog.dismiss();
                }
                PresenterMaster.setInstanceNull();
            }
        });
        PresenterMaster.getInstance(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
        PresenterMaster.getInstance(this).cancel(true);
        PresenterMaster.setInstanceNull();

    }


}