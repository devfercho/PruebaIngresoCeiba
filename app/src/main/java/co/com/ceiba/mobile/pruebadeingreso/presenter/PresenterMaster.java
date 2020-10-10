package co.com.ceiba.mobile.pruebadeingreso.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.database.AppDataBase;
import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.Rest;
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;

import static co.com.ceiba.mobile.pruebadeingreso.presenter.Callback.OK;
import static co.com.ceiba.mobile.pruebadeingreso.presenter.Callback.REST;

public class PresenterMaster extends AsyncTask<MainActivity, Integer, Callback> {

    private static PresenterMaster presenterMaster;
    private AppDataBase db;
    private Rest rest;
    private Activity myActivity;
    private ICallback iCallback;
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

    public static void setInstanceNull() {
        presenterMaster = null;
    }

    public ICallback callback(final ICallback iCallback) {
        this.iCallback = iCallback;
        return this.iCallback;
    }


    @Override
    protected Callback doInBackground(MainActivity... mainActivities) {
        List<User> users = getUsers();

        if (!users.isEmpty()) {
            callback.setUser(users);
            callback.setResult(OK);
        } else {
            callback.setResult(REST);
        }
        return callback;
    }

    @Override
    protected void onPostExecute(Callback callback) {
        super.onPostExecute(callback);
        iCallback.getResult(callback);
    }


    /**
     * Busca los Users en la BD local con Room
     * Si no los encuentra alli, realiza una petición REST
     *
     * @return
     */
    private List<User> getUsers() {
        List<User> users = PresenterMaster.this.db.userDao().getAll();
        if (users.isEmpty())
            rest.getUserRest(myActivity);

        return users;
    }

    /**
     * Inserta los Usuarios en la BD con Room
     *
     * @param users
     */
    public void insertUser(final List<User> users) {
        PresenterMaster.this.db.userDao().insertAll(users);
    }


    /**
     * Inserta los Posts en la BD con Rooom
     *
     * @param posts
     */
    public void insertPost(final List<Post> posts) {
        PresenterMaster.this.db.postDao().insertAll(posts);
    }


    public void getPostByUser(final int idUser, final PostActivity postActivity) {

        final ProgressDialog progressDialog = new ProgressDialog(postActivity);
        progressDialog.setMessage(postActivity.getString(R.string.load_post));
        progressDialog.cancel();
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Post> posts = PresenterMaster.getInstance(myActivity).db.postDao().loadAllByIds(new int[]{idUser});
                if (posts != null && !posts.isEmpty())
                    postActivity.mostrarPosts(posts, progressDialog);
                else
                    PresenterMaster.this.rest.getPostRest(idUser, postActivity, progressDialog);
            }
        }).start();
    }


    public void mostrarUsuarios(final List<User> users) {
        ((MainActivity) myActivity).mostrarUsuarios(users);
    }

    public void mostrarPosts(final List<Post> posts, final PostActivity postActivity, final ProgressDialog progressDialog) {
        postActivity.mostrarPosts(posts, progressDialog);
    }
}
