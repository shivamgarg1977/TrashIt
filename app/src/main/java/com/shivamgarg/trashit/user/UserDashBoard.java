package com.shivamgarg.trashit.user;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.shivamgarg.trashit.R;
import com.shivamgarg.trashit.common.SignUpActivity;

public class UserDashBoard extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    // Hooks of Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView DashboardProfile;
    CoordinatorLayout layout1;


    // Hooks of Layouts
    RelativeLayout userBottomSheet;
    RelativeLayout addOrderLayout;


    // Hooks of Cards
    RelativeLayout SteelCard;
    RelativeLayout GlassCard;
    RelativeLayout RubberCard;
    RelativeLayout PlasticCard;
    TextView steelWeight;
    ImageView steelRemove100;
    ImageView steelAdd100;


    TextView glassWeight;
    ImageView glassRemove100;
    ImageView glassAdd100;


    TextView rubberWeight;
    ImageView rubberRemove100;
    ImageView rubberAdd100;


    TextView plasticWeight;
    ImageView plasticRemove100;
    ImageView plasticAdd100;


    //Hooks of Button
    FloatingActionButton addOrder;
    ExtendedFloatingActionButton takeAPic;
    ImageView nextBtn;

    //Hooks of place pickup drawable
    FrameLayout placePickup;
    TextView pickupWeight;


    //Values for orders
    public double weightOfSteel = 0.0;
    private double weightOfGlass = 0.0;
    private double weightOfRubber = 0.0;
    private double weightOfPlastic = 0.0;
    private static final float END_SCALE=0.7f;
    public double totalWeight=0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_user_dash_board);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Find View By Id
        drawerLayout=findViewById(R.id.user_dashboard_drawer_layout);
        navigationView=findViewById(R.id.user_user_dash_board_navigation_view);


        userBottomSheet = findViewById(R.id.user_user_dash_board_bottom_sheet);
        addOrderLayout = findViewById(R.id.add_order_layout);
        addOrder = findViewById(R.id.add_order);
        takeAPic = findViewById(R.id.camera);
        nextBtn=findViewById(R.id.next_btn);

        SteelCard = findViewById(R.id.layout1_card1);
        steelRemove100=findViewById(R.id.removeSteel100);
        steelAdd100=findViewById(R.id.addSteel100);


        GlassCard = findViewById(R.id.layout1_card2);
        glassRemove100=findViewById(R.id.removeGlass100);
        glassAdd100=findViewById(R.id.addGlass100);


        RubberCard = findViewById(R.id.layout1_card3);
        rubberRemove100=findViewById(R.id.removeRubber100);
        rubberAdd100=findViewById(R.id.addRubber100);


        PlasticCard = findViewById(R.id.layout1_card4);
        plasticRemove100=findViewById(R.id.removePlastic100);
        plasticAdd100=findViewById(R.id.addPlastic100);


        steelWeight = (TextView) findViewById(R.id.steelNumeric);
        glassWeight = (TextView) findViewById(R.id.glassNumeric);
        rubberWeight = (TextView) findViewById(R.id.rubberNumeric);
        plasticWeight = (TextView) findViewById(R.id.plasticNumeric);

        
        DashboardProfile=findViewById(R.id.user_user_dash_board_profile);
        layout1=findViewById(R.id.user_dashboard_coordinator_Layout1);
        placePickup=findViewById(R.id.place_pickup_bottom_sheet);
        pickupWeight=findViewById(R.id.place_pickup_weight);

        //navigation Drawer
        navigationDrawer();



        // Touch Listener on Add Button
        // single and double tap
        addOrder.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    addOrder.setImageResource(R.drawable.user_order_add);

                    addOrderLayout.setVisibility(View.GONE);

                    ViewGroup.LayoutParams params = userBottomSheet.getLayoutParams();
                    // Changes the height and width to the specified *pixels*
                    params.height = 1300;

                    userBottomSheet.setLayoutParams(params);
                    return super.onDoubleTap(e);
                }


                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    Toast.makeText(UserDashBoard.this, "Cards are now Clickable! ", Toast.LENGTH_SHORT).show();
                    addOrder.setImageResource(R.drawable.ic_outline_keyboard_arrow_down_24);
                    ViewGroup.LayoutParams params = userBottomSheet.getLayoutParams();
                    // Changes the height and width to the specified *pixels*
                    params.height = 1800;
                    addOrderLayout.setVisibility(View.VISIBLE);
                    userBottomSheet.setLayoutParams(params);
                    return super.onSingleTapConfirmed(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });


        // Click Listener on Camera
        // single tap
        SteelCard.setOnClickListener(this);
        steelAdd100.setOnClickListener(this);
        steelRemove100.setOnClickListener(this);

        PlasticCard.setOnClickListener(this);
        plasticAdd100.setOnClickListener(this);
        plasticRemove100.setOnClickListener(this);

        GlassCard.setOnClickListener(this);
        glassAdd100.setOnClickListener(this);
        glassRemove100.setOnClickListener(this);

        RubberCard.setOnClickListener(this);
        rubberAdd100.setOnClickListener(this);
        rubberRemove100.setOnClickListener(this);

        takeAPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(takeAPic.getText().toString().equals(" Take a Pic")){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(intent);
                }else{
                    totalWeight=weightOfGlass+weightOfPlastic+weightOfRubber+weightOfSteel;
                    pickupWeight.setText(String.valueOf(totalWeight)+ "KGs");
                    placePickup.setVisibility(View.VISIBLE);

                }

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(takeAPic.getText().toString().equals(" Take a Pic")){
                    takeAPic.setText(" Place Pickup");
                    takeAPic.setIcon(null);
                }else{
                    Drawable img = getResources().getDrawable(R.drawable.camera);
                    takeAPic.setText(" Take a Pic");
                    takeAPic.setIcon(img);
                }
            }
        });

    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.user_nav_home);
        View headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(UserDashBoard.this,
                        UserProfile.class);
                startActivity(profileIntent);
            }
        });

        DashboardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                layout1.setScaleX(offsetScale);
                layout1.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = layout1.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                layout1.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.user_navigation_feedback:
                Intent feedbackIntent = new Intent(UserDashBoard.this,
                        UserFeedback.class);
                startActivity(feedbackIntent);
                break;


        }
        return true;
    }

    @Override
    public void onClick(View v) {
        display();
        switch (v.getId()) {
            case R.id.layout1_card1:
                weightOfSteel += 1;
                break;
            case R.id.layout1_card2:
                weightOfGlass += 1;
                break;
            case R.id.layout1_card3:
                weightOfRubber += 1;
                break;
            case R.id.layout1_card4:
                weightOfPlastic += 1;
                break;
            case R.id.removeSteel100:
                weightOfSteel -=0.1;
                break;
            case R.id.removeGlass100:
                weightOfGlass -=0.1;
                break;
            case R.id.removeRubber100:
                weightOfRubber -=0.1;
                break;
            case R.id.removePlastic100:
                weightOfPlastic -=0.1;
                break;

            case R.id.addSteel100:
                weightOfSteel +=0.1;
                break;
            case R.id.addGlass100:
                weightOfGlass +=0.1;
                break;
            case R.id.addRubber100:
                weightOfRubber +=0.1;
                break;
            case R.id.addPlastic100:
                weightOfPlastic +=0.1;
                break;
        }
        display();
    }


    public void display(){
        steelWeight.setText(String.format("%.1f",weightOfSteel)+" kg");
        glassWeight.setText(String.format("%.1f",weightOfGlass)+" kg");
        rubberWeight.setText(String.format("%.1f",weightOfRubber)+" kg");
        plasticWeight.setText(String.format("%.1f",weightOfPlastic)+" kg");
    }


}

