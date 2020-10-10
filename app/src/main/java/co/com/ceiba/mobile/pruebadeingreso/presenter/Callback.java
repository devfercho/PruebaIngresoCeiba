package co.com.ceiba.mobile.pruebadeingreso.presenter;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.Post;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;

public class Callback {

    public static final int OK = 0;
    public static final int REST = 1;

    private int result;
    private List<User> user;
    private List<Post> posts;

    public Callback getCallback(final Callback callback) {
        return callback;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


}
