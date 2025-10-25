package com.example.myapplication;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Đăng ký với hệ thống
        ImageView img = (ImageView) findViewById(R.id.imageView);
        registerForContextMenu(img);

        // Code xử lý cho button
        View btnPopupMenu = (Button) findViewById(R.id.btn);
        btnPopupMenu.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                // Khởi tạo 1 popupmenu
                PopupMenu popupMenu;
                popupMenu = new PopupMenu(getApplicationContext(), btnPopupMenu);

                // đẩy layout tạo trong xml vào ứng dụng
                popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());

                // Sự kiện click vào item của menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "Bạn vừa chọn popup menu",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    // Code xử lý Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Khi chuẩn bị hiện menu
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // Khi bấm chọn 1 mục trong menu
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }

    // Code xử lý Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}