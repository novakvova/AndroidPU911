package com.example.fox.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox.BaseActivity;
import com.example.fox.ChangeImageActivity;
import com.example.fox.MainActivity;
import com.example.fox.R;
import com.example.fox.account.dto.AccountResponseDTO;
import com.example.fox.account.dto.RegisterDTO;
import com.example.fox.account.dto.ValidationRegisterDTO;
import com.example.fox.account.network.AccountService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {
    private TextView tvInfo;
    private TextInputLayout textFieldEmail;
    private TextInputEditText txtEmail;

    private TextInputLayout textFieldFirstName;
    private TextInputEditText txtFirstName;

    // One Preview Image
    ImageView IVPreviewImage;
    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;
    int SELECT_CROPPER = 300;
    String sImage="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvInfo = findViewById(R.id.tvInfo);
        textFieldEmail = findViewById(R.id.textFieldEmail);
        txtEmail = findViewById(R.id.txtEmail);

        textFieldFirstName = findViewById(R.id.textFieldFirstName);
        txtFirstName = findViewById(R.id.txtFirstName);

        IVPreviewImage = findViewById(R.id.IVPreviewImage);

    }

    public void handleSelectImageClick(View view) {
        Intent intent = new Intent(this, ChangeImageActivity.class);
        startActivityForResult(intent, SELECT_CROPPER);
//        startActivity(intent);
        int a = 12;
//        // create an instance of the
//        // intent of the type image
//        Intent i = new Intent();
//        i.setType("image/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
//
//        // pass the constant to compare it
//        // with the returned requestCode
//        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == SELECT_CROPPER)
        {
            String base64 = data.getStringExtra("base64");

            int a = 12;
            a=16;
        }
        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri uri = data.getData();
                // update the preview image in the layout
                IVPreviewImage.setImageURI(uri);
                Bitmap bitmap= null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // initialize byte stream
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                // compress Bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                // Initialize byte array
                byte[] bytes=stream.toByteArray();
                // get base64 encoded string
                sImage= Base64.encodeToString(bytes,Base64.DEFAULT);
            }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (data == null) {return;}
////        String name = data.getStringExtra("name");
////        tvName.setText("Your name is " + name);
//    }

    public void handleClick(View view) {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail(txtEmail.getText().toString());
        registerDTO.setFirstName(txtFirstName.getText().toString());
        registerDTO.setSecondName("Ivanov");
        registerDTO.setPhone("99382833");
        registerDTO.setPassword("12345");
        registerDTO.setConfirmPassword("12345");
        registerDTO.setPhoto(sImage);

        if (!validationFields(registerDTO))
            return;
        AccountService.getInstance()
                .jsonApi()
                .register(registerDTO)
                .enqueue(new Callback<AccountResponseDTO>() {
                    @Override
                    public void onResponse(Call<AccountResponseDTO> call, Response<AccountResponseDTO> response) {
                        if (response.isSuccessful()) {
                            AccountResponseDTO data = response.body();
                            //JwtSecurityService jwtService = (JwtSecurityService) HomeApplication.getInstance();
                            //jwtService.saveJwtToken(data.getToken());
                            //tvInfo.setText("response is good");
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            try {
                                showErrorsServer(response.errorBody().string());
                            } catch (Exception e) {
                                System.out.println("------Error response parse body-----");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountResponseDTO> call, Throwable t) {
                        String str = t.toString();
                        int a = 12;
                    }
                });
    }

    private boolean validationFields(RegisterDTO registerDTO) {
        textFieldEmail.setError("");
        if (registerDTO.getEmail().equals("")) {
            textFieldEmail.setError("?????????????? ??????????");
            return false;
        }

        textFieldFirstName.setError("");
        if (registerDTO.getFirstName().equals("")) {
            textFieldFirstName.setError("?????????????? ????'??");
            return false;
        }

        textFieldFirstName.setError("");
        if (sImage.equals("")) {
            textFieldFirstName.setError("?????????????? ??????????");
            return false;
        }
        return true;
    }

    private void showErrorsServer(String json) {
        Gson gson = new Gson();
        ValidationRegisterDTO result = gson.fromJson(json, ValidationRegisterDTO.class);
        String str = "";
        if (result.getErrors().getEmail() != null) {
            for (String item : result.getErrors().getEmail()) {
                str += item + "\n";
            }
        }
        textFieldEmail.setError(str);

        str = "";
        if (result.getErrors().getFirstName() != null) {
            for (String item : result.getErrors().getFirstName()) {
                str += item + "\n";
            }
        }
        textFieldFirstName.setError(str);
    }

}