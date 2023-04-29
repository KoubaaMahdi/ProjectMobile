package com.example.fitnessappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class StrengthActivity extends AppCompatActivity {

    private int i;
    private  int[] VideoViewIds = new int[3];
    private String[] VideoIds=new String[3];

    public void init (){
        VideoViewIds[0]=R.id.video_view_Deadlifts;
        VideoViewIds[1]=R.id.video_Bench_Press;
        VideoViewIds[2]=R.id.video_Squats;
        VideoIds[0]="R7AlvUPBQVQ";
        VideoIds[1]="4Y2ZdHCOXok";
        VideoIds[2]="aclHkVaku9U";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength);
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