package co.com.ceiba.mobile.pruebadeingreso.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.database.AppDataBase;
import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.Rest;
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;

import static co.com.ceiba.mobile.pruebadeingreso.presenter.Status.OK;
import static co.com.ceiba.mobile.pruebadeingreso.presenter.Status.REST;

public class PresenterMaster extends AsyncTask<MainActivity, Integer, Callback> {
    public static PresenterMaster presenterMaster;
    private AppDataBase db;
    private Rest rest;
    private Activity myActivity;
    private iCallback iCallback;
    private Callback callback;


    public PresenterMaster(final Activity myActivity) {
        this.db = AppDataBase.getInstance(myActivity.getApplicationContext());
        this.rest = new Rest();
        this.callback = new Callback();
        this.myActivity = myActivity;
    }

    public static PresenterMaster getInstance(final Activity myActivity) {
        if (presenterMaster == null)
            presenterMaster = new PresenterMaster(myActivity);

        return presenterMaster;
    }

    public iCallback callback(final iCallback iCallback) {
        this.iCallback = iCallback;
        return this.iCallback;
    }


    @Override
    protected Callback doInBackground(MainActivity... mainActivities) {
        List<User> users = getUsers();
        if(users != null){
            callback.setUser(users);
            if(!users.isEmpty())
                callback.setResult(OK);
        }else{
            callback.setResult(REST);
        }
        return callback;
    }

    @Override
    protected void onProgressUpdate(final Integer... values) {
        switch (values[0]) {
            case 0:
                //TODO Buscando usuarios BD
                break;
            case 1:
                //TODO Consultando usuarios Services
                break;
            case 2:
                //TODO Guardando Usuarios
            case 3:
                //TODO Buscando posts localmente
                break;
            case 4:
                //TODO Consultando Post Services
                break;
            case 5:
                //TODO Guardando Post BD
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPostExecute(Callback callback) {
        super.onPostExecute(callback);

        iCallback.getResult(callback);
    }

    //TODO: Preguntar a la BD si existen users, si no es asi, haga la consulta REST
    private List<User> getUsers() {
        //TODO: Crear un progressBar

        Log.i("getUsers", "run");
        List<User> users = PresenterMaster.this.db.userDao().getAll();
        if (users.isEmpty()) {
            Log.i("users", "isEmpty");
            rest.getUserRest(myActivity);

        } else {
            for (User user : users) {
                Log.i("user name", user.getName());
            }
        }

        return users;
    }

    public void insertUser(final List<User> users) {
        Log.i("inserUSer", "run");
        PresenterMaster.this.db.userDao().insertAll(users);
        Log.i("len", "" + users.size());
        Log.i("Info", "Users Guardados...");
        for (User user : users) {
            Log.i("User", user.getName());
        }
    }


    public void insertPost(final List<Post> posts) {
        Log.i("inserUSer", "run");
        PresenterMaster.this.db.postDao().insertAll(posts);
        Log.i("len", "" + posts.size());
        Log.i("Info", "Users Guardados...");
        for (Post post : posts) {
            Log.i("Title Post", post.getTitle());
        }
    }


    public void getPostByUser(final int idUser, final PostActivity postActivity){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Post> posts = PresenterMaster.getInstance(myActivity).db.postDao().loadAllByIds(new int[]{idUser});
                if(posts != null && !posts.isEmpty())
                    postActivity.mostrarUsuarios(posts);
                else
                    PresenterMaster.this.rest.getPostRest(idUser, postActivity);
            }
        }).start();
    }


    public void mostrarUsuarios(List <User> users) {
        ((MainActivity)myActivity).mostrarUsuarios(users);
    }

    public void mostrarPosts(List<Post> posts) {
        ((PostActivity)myActivity).mostrarUsuarios(posts);
    }
}
