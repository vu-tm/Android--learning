package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class contactInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView txtName;
        TextView txtEmail;
        TextView txtProject;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactinfo);
        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        txtProject = (TextView) findViewById(R.id.project);
        Button finishBtn = (Button) findViewById(R.id.button);
        finishBtn.setOnClickListener(finishClick);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("nameKey");
        String email = bundle.getString("emailKey");
        String project = bundle.getString("projectKey");
        txtName.setText(name);
        txtEmail.setText(email);
        txtProject.setText(project);
    }
    private View.OnClickListener finishClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
