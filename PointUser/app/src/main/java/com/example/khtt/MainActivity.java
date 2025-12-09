package com.example.khtt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.khtt.data.LoginRepository;
import com.example.khtt.databinding.ActivityMainBinding;
import com.example.khtt.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.add,
                R.id.use,
                R.id.list
        ).build();

        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LoginRepository repo = LoginRepository.getInstance(null);
        if (item.getItemId() == R.id.logout) {
            new AlertDialog.Builder(this)
                    .setTitle("Đăng xuất")
                    .setMessage("Bạn muốn đăng xuất?")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            repo.logout();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }})
                    .setNegativeButton(android.R.string.cancel, null).show();
            return true;
        }

        if (item.getItemId() == R.id.change_password) {
            View dialogView = getLayoutInflater().inflate(R.layout.password_change_dialog, null);
            EditText editText = dialogView.findViewById(R.id.doi_mat_khau);

            new AlertDialog.Builder(this)
                    .setTitle("Đổi mật khẩu - Nhập mật khẩu mới")
                    .setView(dialogView)
                    .setPositiveButton("OK", (dialog, which) -> {
                        String value = editText.getText().toString();
                        repo.changePassword(value);
                    })
                    .setNegativeButton("Cancel", null).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}