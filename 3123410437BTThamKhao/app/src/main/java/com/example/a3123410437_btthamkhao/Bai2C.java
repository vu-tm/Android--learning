package com.example.a3123410437_btthamkhao;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Bai2C extends AppCompatActivity {
    EditText edtContent;
    Button btnSave, btnRead;
    TextView txtResult;
    final String FILE_NAME = "data.txt";
    // Nằm ở data/data/a3123410437_btthamkhao/data.txt

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2c);

        edtContent = findViewById(R.id.edtContent);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        txtResult = findViewById(R.id.txtResult);

        btnSave.setOnClickListener(view -> saveToFile(edtContent.getText().toString()));
        btnRead.setOnClickListener(view -> txtResult.setText(readFromFile()));
    }

    // Lưu file
    private void saveToFile(String data) {
        // try(tài nguyên 1 ; tài nguyên 2 ; ...)
        try (
                FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE); // Ghi byte
                OutputStreamWriter writer = new OutputStreamWriter(fos) // OutputStreamWriter nhận string -> byte
        ) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc file
    private String readFromFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try (
                FileInputStream fis = openFileInput(FILE_NAME); // Đọc byte
                InputStreamReader reader = new InputStreamReader(fis); // InputStreamReader nhận byte -> string
                BufferedReader br = new BufferedReader(reader) // BufferedReader.readLine: đọc nguyên 1 dòng
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}