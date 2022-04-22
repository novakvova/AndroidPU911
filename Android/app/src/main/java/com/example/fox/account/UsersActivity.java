package com.example.fox.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fox.R;
import com.example.fox.account.dto.UserDTO;
import com.example.fox.account.network.AccountService;
import com.example.fox.account.userscard.UsersAdapter;
import com.example.fox.application.HomeApplication;
import com.example.fox.utils.CommonUtils;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private UsersAdapter adapter;
    private RecyclerView rcvUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        rcvUsers = findViewById(R.id.rcvUsers);
        rcvUsers.setHasFixedSize(true);
        rcvUsers.setLayoutManager(new GridLayoutManager(this, 2,
                LinearLayoutManager.VERTICAL, false));

        CommonUtils.showLoading(this);
        AccountService.getInstance()
                .jsonApi()
                .users()
                .enqueue(new Callback<List<UserDTO>>() {
                    @Override
                    public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                        if(response.isSuccessful())
                        {
                            adapter=new UsersAdapter(response.body(),
                                    UsersActivity.this::onClickByItem,
                                    UsersActivity.this::onClickEditItem);
                            rcvUsers.setAdapter(adapter);
                        }
                        CommonUtils.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });

    }
    private void onClickByItem(UserDTO user) {
        Toast.makeText(HomeApplication.getAppContext(), user.getEmail(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(UsersActivity.this, UserActivity.class);
        Bundle b = new Bundle();
        b.putString("email", user.getEmail());
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        //finish();

    }

    private void onClickEditItem(UserDTO user) {
        Toast.makeText(HomeApplication.getAppContext(), "Edit user = "+user.getEmail(),
                Toast.LENGTH_LONG).show();

    }
}
