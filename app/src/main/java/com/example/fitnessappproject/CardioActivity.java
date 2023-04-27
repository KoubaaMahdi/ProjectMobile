package com.example.fitnessappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class CardioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);
        VideoView videoView[] = {findViewById(R.id.video_view_jumping_jacks),findViewById(R.id.video_high_knees),findViewById(R.id.video_burpees)};

        Uri videoUri [] = {Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.exp1),Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.exp2),Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.exp3)};
        // Set optional MediaController to enable controls (such as play/pause) for the video
       for (i = 0 ; i< 3;i++){
           MediaController mediaController = new MediaController(this);
           mediaController.setAnchorView(videoView[i]);
           videoView[i].setMediaController(mediaController);
           videoView[i].setVideoURI(videoUri[i]);

       }

    }
}