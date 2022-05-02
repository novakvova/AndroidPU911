package com.example.fox.account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fox.BaseActivity;
import com.example.fox.R;

public class UserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if(b != null)
            value = b.getString("email");
        TextView tx = findViewById(R.id.textView2);
        tx.setText(value);
    }

}