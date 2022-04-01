package com.example.fox.account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.fox.R;
import com.example.fox.account.dto.AccountResponseDTO;
import com.example.fox.account.dto.RegisterDTO;
import com.example.fox.account.network.AccountService;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout textFieldEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textFieldEmail = findViewById(R.id.textFieldEmail);
    }

    public void handleClick(View view) {
        //Button btn = (Button)view;
//        String text = editTextData.getText().toString();
//        tvInfo.setText(text);
        textFieldEmail.setError("Вкажіть пошту");

//        RegisterDTO registerDTO = new RegisterDTO();
//        registerDTO.setEmail("ss@gmail.com");
//
//        AccountService.getInstance()
//                .jsonApi()
//                .register(registerDTO)
//                .enqueue(new Callback<AccountResponseDTO>() {
//                    @Override
//                    public void onResponse(Call<AccountResponseDTO> call, Response<AccountResponseDTO> response) {
//                        AccountResponseDTO data = response.body();
////                        tvInfo.setText("response is good");
//                    }
//
//                    @Override
//                    public void onFailure(Call<AccountResponseDTO> call, Throwable t) {
//                        String str = t.toString();
//                        int a =12;
//                    }
//                });
    }

}