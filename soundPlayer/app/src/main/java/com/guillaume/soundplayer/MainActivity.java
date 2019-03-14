package com.guillaume.soundplayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.farm);
        mediaPlayer.setLooping(false);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SeekBar seekbarmedia = findViewById(R.id.seekBarMedia);
                seekbarmedia.setProgress(mediaPlayer.getCurrentPosition()*100/mediaPlayer.getDuration());
                handler.postDelayed(this, 1000);
            }
        }, 1000);
        setListener();
        setVolumeEvent();
        setToast();
    }

    public  void setListener()
    {
        findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMediaPlayer();
            }
        });
        findViewById(R.id.btnPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMediaPlayer();
            }
        });
    }

    private void playMediaPlayer()
    {
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
    }

    private void pauseMediaPlayer()
    {
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
    }
    private void setVolumeEvent()
    {
        SeekBar seekbar = findViewById(R.id.seekBarsound);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setSound(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public  void setSound(int volume)
    {
        float log1=(float)(Math.log(50-volume)/Math.log(50));
        mediaPlayer.setVolume(1-log1,1-log1);
    }

    public  void setToast()
    {
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Audio completed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
