package com.example.myapplication;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

    // Bài 1 cách 1
//public class MainActivity extends AppCompatActivity {
//    private View.OnClickListener corkyListener = new View.OnClickListener() {
//        public void onClick(View v) {
//            // do smth khi btn duoc clicked
//            Toast.makeText(MainActivity.this, "Sự kiện click Button",
//                    Toast.LENGTH_SHORT).show();
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Lấy đúng btn từ layout
//        Button button = (Button)findViewById(R.id.btn);
//        // Gán sự kiện
//        button.setOnClickListener(corkyListener);
//    }
//}


    // Bài 1 cách 2
//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    public void onClick(View v) {
//        Toast.makeText(MainActivity.this, "Sự kiện click Button",
//                Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button button = (Button)findViewById(R.id.btn);
//        button.setOnClickListener(this);
//    }
//}


    //Bài 2
//public class MainActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        final EditText editText = (EditText) findViewById(R.id.inputText);
//
//        editText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN
//                        && keyCode == KeyEvent.KEYCODE_ENTER) {
//                    String message = editText.getText().toString();
//                    Toast.makeText(MainActivity.this,message,
//                            Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                return false;
//            }
//        });
//    }
//}


    //Bài 3
//public class MainActivity extends AppCompatActivity{
//    @Override
//        protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        View v1 = findViewById(R.id.view1);
//        v1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction()==MotionEvent.ACTION_DOWN) {
//                    Toast.makeText(MainActivity.this,
//                            "Bạn vừa chạm vào màn hình",
//                            Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//        });
//    }
//}
