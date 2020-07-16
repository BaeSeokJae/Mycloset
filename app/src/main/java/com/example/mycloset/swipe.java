package com.example.mycloset;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;

public class swipe extends Activity {

    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashimage);

        animationView = (LottieAnimationView) findViewById(R.id.swipe_lottie);
        animationView.loop(true);
        animationView.playAnimation(); //Play the animation

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                startLoading();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }// onCreate()..

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();   //현재 액티비티 종료
            }
        }, 2000); // 화면에 Logo 2초간 보이기
    }// startLoading Method..
}// MainActivity Class..
