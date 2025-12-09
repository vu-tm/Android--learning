package com.example.myprofileapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;

public class EditActivity extends AppCompatActivity {
    ImageView avatar;
    ImageView edtAvatar;
    EditText edtName;
    EditText edtEmail;
    Button btnSave;
    SharedPreferences sharedPreferences;
    ActivityResultLauncher<Intent> imagePickerLauncher;
    ActivityResultLauncher<Intent> cameraLauncher;
    ActivityResultLauncher<String> cameraPermissionLauncher;
    String currentImgPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        avatar = findViewById(R.id.avatar);
        edtAvatar = findViewById(R.id.edtAvatar);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        btnSave = findViewById(R.id.btnEdit);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", "Nguyễn Văn A");
        String savedEmail = sharedPreferences.getString("email", "a@gmail.com");
        String savedImgPath = sharedPreferences.getString("img_path", "");
        edtName.setText(savedName);
        edtEmail.setText(savedEmail);
        currentImgPath = savedImgPath;

        if (!savedImgPath.isEmpty()) {
            avatar.setImageURI(Uri.parse(savedImgPath));
        }

        cameraPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) openCamera();
                }
        );

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();

                        if (imageUri != null) {
                            // Xin quyền truy cập lâu dài
                            getContentResolver().takePersistableUriPermission(
                                    imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                            );
                        }

                        avatar.setImageURI(imageUri);
                        currentImgPath = imageUri.toString(); // lưu uri mới
                    }
                }
        );

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bitmap imageBitmap = (Bitmap) result.getData().getExtras().get("data");
                        if (imageBitmap != null) {
                            Uri imageUri = saveBitmapToCache(imageBitmap);
                            if (imageUri != null) {
                                avatar.setImageBitmap(imageBitmap);
                                currentImgPath = imageUri.toString();
                            }
                        }
                    }
                }
        );

        edtAvatar.setOnClickListener(view -> {
            // Tạo dialog layout
            LayoutInflater inflater = LayoutInflater.from(this);
            View dialogView = inflater.inflate(R.layout.chooser_dialog, null);

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setTitle("Chọn ảnh từ")
                    .create();

            Button btnCamera = dialogView.findViewById(R.id.btnCamera);
            Button btnGallery = dialogView.findViewById(R.id.btnGallery);
            Button btnCancel = dialogView.findViewById(R.id.btnCancel);

            btnCamera.setOnClickListener(v -> {
                dialog.dismiss();
                checkCameraPermission();
            });

            btnGallery.setOnClickListener(v -> {
                dialog.dismiss();
                openGallery();
            });

            btnCancel.setOnClickListener(v -> dialog.dismiss());

            dialog.show();
        });

        btnSave.setOnClickListener(view -> {
            String editedName = edtName.getText().toString();
            String editedEmail = edtEmail.getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", editedName);
            editor.putString("email", editedEmail);
            editor.putString("img_path", currentImgPath);
            editor.apply();

            // Kết thúc
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(intent);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        imagePickerLauncher.launch(intent);
    }

    private Uri saveBitmapToCache(Bitmap bitmap) {
        try {
            // Tạo file trong cache
            File cachePath = new File(getCacheDir(), "images");
            cachePath.mkdirs();
            String fileName = "profile_" + System.currentTimeMillis() + ".jpg";
            File imageFile = new File(cachePath, fileName);

            // Lưu bitmap
            FileOutputStream stream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.close();

            // Trả về URI
            return Uri.fromFile(imageFile); // Nhận đối tượng file trả về uri
        } catch (Exception e) {
            return null;
        }
    }
}