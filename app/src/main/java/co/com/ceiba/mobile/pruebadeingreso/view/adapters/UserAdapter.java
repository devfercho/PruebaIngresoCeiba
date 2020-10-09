package co.com.ceiba.mobile.pruebadeingreso.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMaster;
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUser> {

    private List<User> listUser;

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    private List<User> listUserFull;
    private Context context;

    public UserAdapter(List<User> listUser, Context context) {
        this.listUser = listUser;
        this.context = context;
    }

    @android.support.annotation.NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@android.support.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolderUser(view);
    }

    @Override
    public void onBindViewHolder(@android.support.annotation.NonNull ViewHolderUser holder, int position) {
        final User user = listUser.get(position);
        holder.tvName.setText(user.getName());
        holder.tvPhone.setText(user.getPhone());
        holder.tvEmail.setText(user.getEmail());
        holder.btnPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               instanciarPostActivity(user);
            }
        });
    }

    private void instanciarPostActivity(final User user) {
        Intent intent = new Intent(context, PostActivity.class);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }



    public void filterList(List<User> userFilter) {
        listUser = userFilter;
        notifyDataSetChanged();
    }


    public class ViewHolderUser extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPhone;
        private TextView tvEmail;
        private Button btnPosts;

        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvPhone = itemView.findViewById(R.id.phone);
            tvEmail = itemView.findViewById(R.id.email);
            btnPosts = itemView.findViewById(R.id.btn_view_post);
        }
    }
}
