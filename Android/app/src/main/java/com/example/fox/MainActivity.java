package com.example.fox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.fox.constants.Urls;
import com.example.fox.network.ImageRequester;

public class MainActivity extends AppCompatActivity {

    TextView txtInfo;
    EditText editInfo;
    private ImageRequester imageRequester;
    private NetworkImageView myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInfo = findViewById(R.id.txtInfo);
        editInfo = findViewById(R.id.editInfo);

        imageRequester = ImageRequester.getInstance();
        myImage = findViewById(R.id.myimg);
        String urlImg = Urls.BASE+"/images/1.jpg";
        imageRequester.setImageFromUrl(myImage, urlImg);



    }
    public void onClickBtn(View view) {
        String text = editInfo.getText().toString();
        txtInfo.setText(text);
    }
}