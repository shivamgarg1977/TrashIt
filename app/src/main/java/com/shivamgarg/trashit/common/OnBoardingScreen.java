package com.shivamgarg.trashit.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.shivamgarg.trashit.R;
import com.shivamgarg.trashit.helperClasses.commonAdapter.SliderAdapter;
import com.shivamgarg.trashit.user.UserDashBoard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoardingScreen extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dots_layout;
    Animation animation;
    SliderAdapter sliderAdapter;
    Button letsGetStarted;
    TextView[] dots;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.common_on_boarding_screen);
        viewPager= findViewById(R.id.viewPager);
        letsGetStarted=findViewById(R.id.lets_get_started);
        dots_layout=findViewById(R.id.dots);

        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);


        letsGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OnBoardingScreen.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }
    public void skip(View view){
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }
    public void next(View view){

        viewPager.setCurrentItem(currentPos+1);

    }
    public void addDots(int position){
        dots = new TextView[4];
        dots_layout.removeAllViews();
        for(int i=0;i<dots.length;i++){
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);


            dots_layout.addView(dots[i]);
        }

        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.smith_apple));
        }
    }

    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);
            currentPos=position;
            if(position<3){
                letsGetStarted.setVisibility(View.INVISIBLE);
            }else{
                animation = AnimationUtils.loadAnimation(OnBoardingScreen.this,R.anim.side_anim);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



}