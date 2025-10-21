package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtName = findViewById(R.id.txtName);
        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtProject = findViewById(R.id.txtProject);

        // Xử lý button
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getContactInfo = new Intent(getApplicationContext(), contactInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameKey", txtName.getText().toString());
                bundle.putString("emailKey", txtEmail.getText().toString());
                bundle.putString("projectKey", txtProject.getText().toString());
                getContactInfo.putExtras(bundle);

                startActivity(getContactInfo);
            }
        });
    }
}