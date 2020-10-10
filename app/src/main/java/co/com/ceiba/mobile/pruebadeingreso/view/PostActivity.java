package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.PostAdapter;

public class PostActivity extends AppCompatActivity {

    private TextView tvNameUser;
    private TextView tvPhoneUser;
    private TextView tvMailUser;

    private User user;

    private RecyclerView recyclerPost;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        recyclerPost = findViewById(R.id.recyclerViewPostsResults);

        tvNameUser = findViewById(R.id.name);
        tvPhoneUser = findViewById(R.id.phone);
        tvMailUser = findViewById(R.id.email);

        llenarDatosUser();
        desplegarPost();
    }

    private void desplegarPost() {
        PresenterMaster.getInstance(this).getPostByUser(user.getIdUser(), this);
    }

    private void llenarDatosUser() {
        user = (User) getIntent().getSerializableExtra("user");
        tvNameUser.setText(user.getName());
        tvPhoneUser.setText(user.getPhone());
        tvMailUser.setText(user.getEmail());
    }

    public void mostrarPosts(final List<Post> posts, final ProgressDialog progressDialog) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Arma el recycler y despliega los usuarios
                postAdapter = new PostAdapter(posts);
                recyclerPost.setLayoutManager(new LinearLayoutManager(PostActivity.this.getApplicationContext()));
                recyclerPost.setAdapter(postAdapter);
                progressDialog.dismiss();

            }
        });
    }
}
