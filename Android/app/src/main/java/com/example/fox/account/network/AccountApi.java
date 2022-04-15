package com.example.fox.account.network;

import com.example.fox.account.dto.AccountResponseDTO;
import com.example.fox.account.dto.LoginDTO;
import com.example.fox.account.dto.RegisterDTO;
import com.example.fox.account.dto.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("/api/account/register")
    public Call<AccountResponseDTO> register(@Body RegisterDTO model);
    @POST("/api/account/login")
    public Call<AccountResponseDTO> login(@Body LoginDTO model);
    @GET("/api/account/users")
    public Call<List<UserDTO>> users();
}
