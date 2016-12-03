package com.appytech.businessway.tools;

import android.graphics.Rect;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by eng_m on 9/13/2016.
 */
public class AnimationManager {

//    public static void startFunnyLogoAnimation(final View mainLayout) {
//        // set a global layout listener which will be called when the layout pass is completed and the view is drawn
//        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(
//                new ViewTreeObserver.OnGlobalLayoutListener() {
//                    public void onGlobalLayout() {
//                        //Remove the listener before proceeding
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                            mainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                        } else {
//                            mainLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                        }
//
//                        int startDelayDuration=1000;
//                        int transformDuration=1700;
//                        int fadeDuration=1000;
//                        transform(mainLayout, R.id.splash_init_f_imageView, R.id.splash_f_imageView, false, transformDuration, startDelayDuration);
//                        transform(mainLayout, R.id.splash_init_o1_imageView, R.id.splash_o1_imageView, false, transformDuration, startDelayDuration);
//                        transform(mainLayout, R.id.splash_init_o2_imageView, R.id.splash_o2_imageView, false, transformDuration, startDelayDuration);
//                        transform(mainLayout, R.id.splash_init_d_imageView, R.id.splash_d_imageView, true, transformDuration, startDelayDuration);
//                        transform(mainLayout, R.id.splash_init_y_imageView, R.id.splash_y_imageView, false, transformDuration, startDelayDuration);
//
////						findViewById(R.id.splash_logo_imageView).animate().alpha(1).setDuration(1000).setStartDelay(transformDuration);
//
//                        mainLayout.findViewById(R.id.splash_logo_imageView).setVisibility(View.VISIBLE);
//                        Animation fadeIn = new AlphaAnimation(0, 1);
//                        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
//                        fadeIn.setDuration(fadeDuration);
//                        fadeIn.setStartOffset(startDelayDuration+transformDuration);
//                        mainLayout.findViewById(R.id.splash_logo_imageView).startAnimation(fadeIn);
////						new Handler().postDelayed(new Runnable() {
////							@Override
////							public void run() {
////								findViewById(R.id.splash_init_f_imageView).setVisibility(View.INVISIBLE);
////								findViewById(R.id.splash_init_o1_imageView).setVisibility(View.INVISIBLE);
////								findViewById(R.id.splash_init_o2_imageView).setVisibility(View.INVISIBLE);
////								findViewById(R.id.splash_init_d_imageView).setVisibility(View.INVISIBLE);
////								findViewById(R.id.splash_init_y_imageView).setVisibility(View.INVISIBLE);
////							}
////						},transformDuration+startDelayDuration+fadeDuration);
//                    }
//                });
//    }

    public static void transform(View sourceView, View destinationView, int rotationDegree, int duration, int delayDuration){
//        ImageView sourceView=(ImageView)mainLayout.findViewById(source);
//        ImageView destinationView=(ImageView)mainLayout.findViewById(destination);

        Rect sourceRect = new Rect();
        Rect destinationRect = new Rect();
        destinationView.getGlobalVisibleRect(sourceRect);
        sourceView.getGlobalVisibleRect(destinationRect);

        int xTranslation=((sourceRect.right-destinationRect.right)+(sourceRect.left-destinationRect.left))/2;
        int yTranslation=((sourceRect.top-destinationRect.top)+(sourceRect.bottom-destinationRect.bottom))/2;
        sourceView.animate().translationX(xTranslation).setDuration(duration).setStartDelay(delayDuration);
        sourceView.animate().translationY(yTranslation).setDuration(duration).setStartDelay(delayDuration);

        float xScale=(float) sourceRect.width()/(float) destinationRect.width();
        float yScale=(float) sourceRect.height()/(float) destinationRect.height();
        if(rotationDegree!=0){
            if(rotationDegree%90==0) {
                xScale = (float) sourceRect.width() / (float) destinationRect.height();
                yScale = (float) sourceRect.height() / (float) destinationRect.width();
            }
            sourceView.animate().rotation(rotationDegree).setDuration(duration);
        }
        sourceView.animate().scaleX(xScale).setDuration(duration).setStartDelay(delayDuration);
        sourceView.animate().scaleY(yScale).setDuration(duration).setStartDelay(delayDuration);
//		Log.e("WIDTH        :", String.valueOf(sourceRect.width()));
//		Log.e("HEIGHT       :", String.valueOf(sourceRect.height()));
//		Log.e("left         :", String.valueOf(sourceRect.left));
//		Log.e("right        :", String.valueOf(sourceRect.right));
//		Log.e("top          :", String.valueOf(sourceRect.top));
//		Log.e("bottom       :", String.valueOf(sourceRect.bottom));
    }

    public static void slideDown(View view, int duration, int delayDuration){
        translate(view, duration, delayDuration, 0, 0, 0, view.getHeight());
    }

    public static void slideFromDown(View view, int duration, int delayDuration){
        translate(view, duration, delayDuration, 0, 0, view.getHeight(), 0);
    }

    public static void slideFromDownWithDistance(View view, int distance, int duration, int delayDuration){
        translate(view, duration, delayDuration, 0, 0, distance, 0);
    }

    private static void translate(final View view, int duration, int delayDuration, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        TranslateAnimation animate = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        animate.setDuration(duration);
        animate.setFillAfter(true);
        animate.setStartOffset(delayDuration);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    public static void fadeIn(View view, int duration){
        Animation fadeInAnimation = new AlphaAnimation(0, 1);
        fadeInAnimation.setInterpolator(new DecelerateInterpolator());
        fadeInAnimation.setDuration(duration);
        view.startAnimation(fadeInAnimation);
        view.setVisibility(View.VISIBLE);
    }

    public static void fadeOut(View view, int duration){
        Animation fadeOutAnimation = new AlphaAnimation(1, 0);
        fadeOutAnimation.setInterpolator(new AccelerateInterpolator());
//        fadeOutAnimation.setStartOffset(1000);
        fadeOutAnimation.setDuration(duration);
        view.startAnimation(fadeOutAnimation);
        view.setVisibility(View.INVISIBLE);
    }
}
