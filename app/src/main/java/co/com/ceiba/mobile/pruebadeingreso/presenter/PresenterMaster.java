package co.com.ceiba.mobile.pruebadeingreso.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.database.AppDataBase;
import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.Rest;
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity;

public class PresenterMaster extends AsyncTask<MainActivity, Integer, Callback> {
    public static PresenterMaster presenterMaster;
    private AppDataBase db;
    private Rest rest;
    private Context myContext;
    private iCallback iCallback;
    private Callback callback;

    public PresenterMaster(final Context context) {
        this.db = AppDataBase.getInstance(context);
        this.rest = new Rest();
    }

    public static PresenterMaster getInstance(final Context context) {
        if (presenterMaster == null)
            presenterMaster = new PresenterMaster(context);

        return presenterMaster;
    }

    public iCallback callback(final iCallback iCallback) {
        this.iCallback = iCallback;
        return this.iCallback;
    }


    @Override
    protected Callback doInBackground(MainActivity... mainActivities) {

        List<User> users = getUsers();
        callback.setUser(users);

        List<Post> posts = getPost();
        callback.setPosts(posts);


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
            rest.getUserRest(myContext);
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

    //TODO: Preguntar a la BD si existen users, si no es asi, haga la consulta REST
    private List<Post> getPost() {
        //TODO: Crear un progressBar

        Log.i("getUsers", "run");
        List<Post> posts = PresenterMaster.this.db.postDao().getAll();
        if (posts.isEmpty()) {
            Log.i("users", "isEmpty");
            rest.getUserRest(myContext);
        } else {
            for (Post post : posts) {
                Log.i("Post Title", post.getTitle());
            }
        }

        return null;
    }


}
