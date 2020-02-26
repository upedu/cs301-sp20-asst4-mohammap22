package edu.up.cs301.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.animation.Animator;

public class RpsActivity extends AppCompatActivity {

    private RpsAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rps);


        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface) this.findViewById(R.id.animationSurface);
        animator = new RpsAnimator();
        mySurface.setAnimator(animator);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.add(1);
            }
        });


    }


}

