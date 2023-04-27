package com.example.fitnessappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class CardioActivity extends AppCompatActivity {

    /*private RecyclerView recyclerView;
    private CardioExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);

        recyclerView = findViewById(R.id.cardio_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CardioExerciseAdapter(this,getCardioExercises());
        recyclerView.setAdapter(adapter);
    }

    private List<CardioExercise> getCardioExercises() {
        List<CardioExercise> cardioExercises = new ArrayList<>();

        cardioExercises.add(new CardioExercise("Jumping Jacks", "Jumping jacks are a great way to get your heart rate up and improve your cardiovascular endurance. To perform this exercise, start with your feet together and your arms at your sides. Then, jump your feet out to the sides while simultaneously raising your arms above your head. Jump back to the starting position and repeat.", R.raw.exp1));
        cardioExercises.add(new CardioExercise("High Knees", "High knees are another great cardio exercise that can improve your endurance and help you burn calories. To perform this exercise, stand with your feet hip-width apart and your arms at your sides. Then, lift one knee up towards your chest, while hopping on the other foot. Lower the lifted knee and repeat with the other leg.", R.raw.exp2));
        cardioExercises.add(new CardioExercise("Burpees", "Burpees are a challenging cardio exercise that can work your entire body. To perform this exercise, start in a standing position, then drop down into a plank position. Do a push-up, then jump your feet back towards your hands and jump up into the air with your arms raised. Land softly and repeat the exercise.", R.raw.exp3));

        return cardioExercises;
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);
        int i;
        int videoView[] = {R.id.video_view_jumping_jacks,R.id.video_high_knees,R.id.video_burpees};
        String VideoIds []={"iSSAk4XCsRA","oDdkytliOqE","dZgVxmf6jkA"};
        // Set optional MediaController to enable controls (such as play/pause) for the video
        for (i = 0 ; i< videoView.length;i++){
            YouTubePlayerView youTubePlayerView = findViewById(videoView[i]);
            getLifecycle().addObserver(youTubePlayerView);
            int finalI = i;
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = VideoIds[finalI];
                    youTubePlayer.cueVideo(videoId, 0);
                }
            });
            youTubePlayerView.addFullscreenListener(new FullscreenListener() {
                @Override
                public void onEnterFullscreen(@NonNull View view, @NonNull Function0<Unit> function0) {

                }
                @Override
                public void onExitFullscreen() {
                }
            });

        }

    }
}
