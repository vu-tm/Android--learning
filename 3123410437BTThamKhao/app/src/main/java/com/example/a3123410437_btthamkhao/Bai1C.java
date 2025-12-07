package com.example.a3123410437_btthamkhao;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai1C extends AppCompatActivity {
    EditText edtName;
    Button btnSave;
    TextView txtResult;
    SharedPreferences sharedPreferences; // Lưu dữ liệu dạng key-value trong bộ nhớ của ứng dụng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai1c);

        edtName = findViewById(R.id.edtName);
        btnSave = findViewById(R.id.btnSave);
        txtResult = findViewById(R.id.txtResult);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE); //(name, quyền truy cập file)

        // Đọc dữ liệu khi mở lại app
        String savedName = sharedPreferences.getString("name", "Chưa có dữ liệu"); // SharedPreferences, Context -> getString
        txtResult.setText("Tên đã lưu: " + savedName);

        btnSave.setOnClickListener(view -> { // Khi ng dùng bấm save
            String name = edtName.getText().toString(); // EditText, TextView -> getText().toString()
            SharedPreferences.Editor editor = sharedPreferences.edit(); // Chỉ editor cho thay đổi trên SharedPreferences
            editor.putString("name", name);
            editor.apply(); // editor có thể lưu bằng void apply(bất đồng bộ) / boolean commit (đồng bộ) -> nên dùng apply()

            txtResult.setText("Tên đã lưu: " + name);
        });
    }
}
