package com.example.a3123410437_btthamkhao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Bai3C extends AppCompatActivity {
    EditText edtName, edtAge;
    Button btnAdd;
    ListView listView;
    DatabaseHelper databaseHelper;
    ArrayList<String> studentList;
    ArrayAdapter<String> adapter; // Kết nối data với ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai3c);

        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);

        databaseHelper = new DatabaseHelper(this);
        loadStudentList();

        btnAdd.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            int age = Integer.parseInt(edtAge.getText().toString());
            databaseHelper.addStudent(name, age);
            loadStudentList();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = studentList.get(position);
            String[] parts = selectedItem.split(" - "); // Tách chuỗi để lấy id
            int studentId = Integer.parseInt(parts[0]);

            databaseHelper.deleteStudent(studentId);
            loadStudentList();
            Toast.makeText(Bai3C.this, "Đã xóa sinh viên ID: " + studentId, Toast.LENGTH_SHORT).show();
        });
    }

    private void loadStudentList() {
        studentList = databaseHelper.getAllStudents();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listView.setAdapter(adapter);
    }

}
