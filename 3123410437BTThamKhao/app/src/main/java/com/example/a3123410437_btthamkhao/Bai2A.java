package com.example.a3123410437_btthamkhao;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Bai2A extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    String audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2a); // Hiện xml lên màn hình

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnStop = findViewById(R.id.btnStop);

        btnPlay.setOnClickListener(view -> {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();

                try {
                    mediaPlayer.setDataSource(audioUrl);
                    mediaPlayer.prepareAsync(); // chạy ngầm
                    mediaPlayer.setOnPreparedListener(MediaPlayer::start); // Lấy hàm start không tham số của MediaPlayer
                } catch (IOException e) {
                    e.printStackTrace(); // In lỗi ra logcat
                }
            }
        });

        btnStop.setOnClickListener(view -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });


    }
}
