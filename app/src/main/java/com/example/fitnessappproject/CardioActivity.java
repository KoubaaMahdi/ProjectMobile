package com.example.fitnessappproject;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class CardioActivity extends AppCompatActivity {
    private int i;
    private  int[] VideoViewIds = new int[3];
    private String[] VideoIds=new String[3];

    public void init (){
        VideoViewIds[0]=R.id.video_view_jumping_jacks;
        VideoViewIds[1]=R.id.video_high_knees;
        VideoViewIds[2]=R.id.video_burpees;
        VideoIds[0]="iSSAk4XCsRA";
        VideoIds[1]="oDdkytliOqE";
        VideoIds[2]="dZgVxmf6jkA";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);
        for (i = 0 ; i< VideoViewIds.length;i++){
            YouTubePlayerView youTubePlayerView = findViewById(VideoViewIds[i]);
            getLifecycle().addObserver(youTubePlayerView);
            final int finalI=i;
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = VideoIds[finalI];
                    youTubePlayer.cueVideo(videoId, 0);
                }
            });

        }

    }
}
