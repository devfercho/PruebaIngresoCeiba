package co.com.ceiba.mobile.pruebadeingreso.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.dto.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolderPost> {

    private List<Post> listPost;

    public PostAdapter(List<Post> listPost) {
        this.listPost = listPost;
    }

    @android.support.annotation.NonNull
    @Override
    public ViewHolderPost onCreateViewHolder(@android.support.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        return new ViewHolderPost(view);
    }

    @Override
    public void onBindViewHolder(@android.support.annotation.NonNull ViewHolderPost holder, int position) {
        Post post = listPost.get(position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }


    public class ViewHolderPost extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvBody;

        public ViewHolderPost(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvBody = itemView.findViewById(R.id.body);
        }
    }
}