package com.shivamgarg.trashit.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shivamgarg.trashit.R;
import com.shivamgarg.trashit.common.LoginActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDashBoard extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;
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

    //second hooks of cards
    RelativeLayout PaperCard;
    RelativeLayout DevicesCard;
    RelativeLayout WiresCard;
    RelativeLayout DonateCard;
    // aman

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

    // sheet of card 2
    //paper list
    TextView paperWeight;
    ImageView paperRemove100;
    ImageView paperAdd100;

    //devices list
    TextView devicesWeight;
    ImageView devicesRemove100;
    ImageView devicesAdd100;

    //Wires list
    TextView wiresWeight;
    ImageView wiresRemove100;
    ImageView wiresAdd100;

    //


    //Hooks of Button
    FloatingActionButton addOrder;
    ExtendedFloatingActionButton takeAPic;
    ImageView nextBtn;

    //Hooks of place pickup drawable
    FrameLayout placePickup;
    TextView pickupWeight;
    TextInputEditText userAddress;
    TextInputEditText userPincode;
    ExtendedFloatingActionButton placePickupButton;
    CheckBox userTerm;


    //Values for orders
    public double weightOfSteel = 0.0;
    private double weightOfGlass = 0.0;
    private double weightOfRubber = 0.0;
    private double weightOfPlastic = 0.0;
    private double weightOfPaper =0.0;
    private double weightOfDevices =0.0;
    private double weightOfWires =0.0;


    private static final float END_SCALE = 0.7f;
    public double totalWeight = 0.0;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String uid;
    private String address="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_user_dash_board);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Users");
        String Uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        uid=Uid;

        // Find View By Id
        drawerLayout = findViewById(R.id.user_dashboard_drawer_layout);
        navigationView = findViewById(R.id.user_user_dash_board_navigation_view);


        userBottomSheet = findViewById(R.id.user_user_dash_board_bottom_sheet);
        addOrderLayout = findViewById(R.id.add_order_layout);
        addOrder = findViewById(R.id.add_order);
        takeAPic = findViewById(R.id.camera);
        nextBtn = findViewById(R.id.next_btn);

        SteelCard = findViewById(R.id.layout1_card1);
        steelRemove100 = findViewById(R.id.removeSteel100);
        steelAdd100 = findViewById(R.id.addSteel100);


        GlassCard = findViewById(R.id.layout1_card2);
        glassRemove100 = findViewById(R.id.removeGlass100);
        glassAdd100 = findViewById(R.id.addGlass100);


        RubberCard = findViewById(R.id.layout1_card3);
        rubberRemove100 = findViewById(R.id.removeRubber100);
        rubberAdd100 = findViewById(R.id.addRubber100);


        PlasticCard = findViewById(R.id.layout1_card4);
        plasticRemove100 = findViewById(R.id.removePlastic100);
        plasticAdd100 = findViewById(R.id.addPlastic100);

        PaperCard= findViewById(R.id.layout2_card1);
        paperRemove100 = findViewById(R.id.removePaper100);
        paperAdd100 = findViewById(R.id.addPaper100);

        DevicesCard = findViewById(R.id.layout2card2);
        devicesRemove100 = findViewById(R.id.removeDevice100);
        devicesAdd100 = findViewById(R.id.addDevice100);

        WiresCard = findViewById(R.id.layout2_card3);
        wiresRemove100 = findViewById(R.id.removeWire);
        wiresAdd100 = findViewById(R.id.addWire);





        steelWeight = (TextView) findViewById(R.id.steelNumeric);
        glassWeight = (TextView) findViewById(R.id.glassNumeric);
        rubberWeight = (TextView) findViewById(R.id.rubberNumeric);
        plasticWeight = (TextView) findViewById(R.id.plasticNumeric);

        // second layout card
        paperWeight = findViewById(R.id.paperNumeric);
        devicesWeight =findViewById(R.id.devicesNumeric);
        wiresWeight = findViewById(R.id.wiresNumeric);


        DashboardProfile = findViewById(R.id.user_user_dash_board_profile);
        layout1 = findViewById(R.id.user_dashboard_coordinator_Layout1);
        placePickup = findViewById(R.id.place_pickup_bottom_sheet);
        pickupWeight = findViewById(R.id.place_pickup_weight);
        userAddress=findViewById(R.id.user_address);
        userPincode=findViewById(R.id.user_pincode);
        placePickupButton=findViewById(R.id.place_pickup);
        userTerm=findViewById(R.id.user_terms);


        ref.child(Uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){

                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot= task.getResult();

                        TextView profileName=navigationView.getHeaderView(0).findViewById(R.id.nav_header_full_name);
                        profileName.setText(String.valueOf(dataSnapshot.child("fullName").getValue()));
                        fullName=String.valueOf(dataSnapshot.child("fullName").getValue());
                        email=String.valueOf(dataSnapshot.child("email").getValue());
                        phoneNumber=String.valueOf(dataSnapshot.child("phoneNumber").getValue());
                        if(String.valueOf(dataSnapshot.child("address").getValue())!=null){
                            address=String.valueOf(dataSnapshot.child("address").getValue());
                            userAddress.setText(address);
                        }

                    }else{
                        Toast.makeText(UserDashBoard.this, "User Doesn't exists", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(UserDashBoard.this, "Failed! to fetch data", Toast.LENGTH_SHORT).show();
                }
            }
        });





        //navigation Drawer
        navigationDrawer();





        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addOrderLayout.getVisibility() == View.VISIBLE) {
                    addOrder.setImageResource(R.drawable.user_order_add);

                    addOrderLayout.setVisibility(View.GONE);

                    ViewGroup.LayoutParams params = userBottomSheet.getLayoutParams();
                    // Changes the height and width to the specified *pixels*
                    params.height = 1400;

                    userBottomSheet.setLayoutParams(params);
                } else {
                    Toast.makeText(UserDashBoard.this, "Cards are now Clickable! ", Toast.LENGTH_SHORT).show();
                    addOrder.setImageResource(R.drawable.ic_outline_keyboard_arrow_down_24);
                    ViewGroup.LayoutParams params = userBottomSheet.getLayoutParams();
                    // Changes the height and width to the specified *pixels*
                    params.height = 1800;
                    addOrderLayout.setVisibility(View.VISIBLE);
                    userBottomSheet.setLayoutParams(params);
                }
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

        PaperCard.setOnClickListener(this);
        paperAdd100.setOnClickListener(this);
        paperRemove100.setOnClickListener(this);

        DevicesCard.setOnClickListener(this);
        devicesAdd100.setOnClickListener(this);
        devicesRemove100.setOnClickListener(this);

        WiresCard.setOnClickListener(this);
        wiresAdd100.setOnClickListener(this);
        wiresRemove100.setOnClickListener(this);





        takeAPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(intent);
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalWeight = weightOfGlass + weightOfPlastic + weightOfRubber + weightOfSteel + weightOfPaper +weightOfDevices +weightOfWires;
                pickupWeight.setText(String.valueOf(totalWeight) + "KGs");
                placePickup.setVisibility(View.VISIBLE);
            }
        });
        placePickupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userTerm.isChecked()){
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    Date date=new Date();
                    String dateString=String.valueOf(simpleDateFormat.format(date));
                    userTerm.setError(null);
                    ref.child(uid).child("Orders").child(dateString).child("userAddress").setValue(userAddress.getText().toString());
                    ref.child(uid).child("Orders").child(dateString).child("userPincode").setValue(userPincode.getText().toString());
                    ref.child(uid).child("Orders").child(dateString).child("scrapWeight").setValue(String.valueOf(totalWeight));
                    ref.child(uid).child("Orders").child(dateString).child("date").setValue(dateString);

                    Toast.makeText(UserDashBoard.this, "Order Placed", Toast.LENGTH_SHORT).show();
                    placePickup.setVisibility(View.GONE);
                }else{

                    userTerm.setError("Accepts the terms ");
                }
            }
        });


    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.user_nav_home);
        View headerView = navigationView.getHeaderView(0);
        ;
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(UserDashBoard.this,
                        UserProfile.class);
                profileIntent.putExtra("fullName",fullName);
                profileIntent.putExtra("email",email);
                profileIntent.putExtra("phoneNumber",phoneNumber);
                profileIntent.putExtra("Uid",uid);
                profileIntent.putExtra("address",address);
                startActivity(profileIntent);
            }
        });

        DashboardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
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
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (placePickup.getVisibility() == View.VISIBLE) {
            placePickup.setVisibility(View.INVISIBLE);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_navigation_feedback:
                Intent feedbackIntent = new Intent(UserDashBoard.this,
                        UserFeedback.class);
                startActivity(feedbackIntent);
                break;
            case R.id.user_nav_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.user_navigation_log_out:
                mAuth.signOut();
                Intent logoutIntent = new Intent(UserDashBoard.this,
                        LoginActivity.class);
                startActivity(logoutIntent);
                finish();
                break;
            case R.id.user_navigation_report:
                Intent reportIntent = new Intent(UserDashBoard.this,
                        ReportABug.class);

                startActivity(reportIntent);
                break;
            case R.id.user_navigation_order_in_progress:
                Intent progressIntent = new Intent(UserDashBoard.this,
                        OrderInProgress.class);
                progressIntent.putExtra("Uid",uid);
                startActivity(progressIntent);
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
            case R.id.layout2_card1:
                weightOfPaper += 1;
                break;
            case R.id.layout2card2:
                weightOfDevices += 1;
                break;
            case R.id.layout2_card3:
                weightOfWires += 1;
                break;
            case R.id.removeSteel100:
                weightOfSteel -= 0.1;
                break;
            case R.id.removeGlass100:
                weightOfGlass -= 0.1;
                break;
            case R.id.removeRubber100:
                weightOfRubber -= 0.1;
                break;
            case R.id.removePlastic100:
                weightOfPlastic -= 0.1;
                break;
            case R.id.removePaper100:
                weightOfPaper -= 0.1;
                break;
            case R.id.removeDevice100:
                weightOfDevices -= 0.1;
                break;
            case R.id.removeWire:
                weightOfWires -= 0.1;
                break;

            case R.id.addSteel100:
                weightOfSteel += 0.1;
                break;
            case R.id.addGlass100:
                weightOfGlass += 0.1;
                break;
            case R.id.addRubber100:
                weightOfRubber += 0.1;
                break;
            case R.id.addPlastic100:
                weightOfPlastic += 0.1;
                break;
            case R.id.addPaper100:
                weightOfPaper += 0.1;
                break;
            case R.id.addDevice100:
                weightOfDevices += 0.1;
                break;
            case R.id.addWire:
                weightOfWires += 0.1;
                break;

        }
        display();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }


    public void display() {
        steelWeight.setText(String.format("%.1f", weightOfSteel) + " KG");
        glassWeight.setText(String.format("%.1f", weightOfGlass) + " KG");
        rubberWeight.setText(String.format("%.1f", weightOfRubber) + " KG");
        plasticWeight.setText(String.format("%.1f", weightOfPlastic) + " KG");
        paperWeight.setText(String.format("%.1f", weightOfPaper) + " KG");
        devicesWeight.setText(String.format("%.1f", weightOfDevices) + " KG");
        wiresWeight.setText(String.format("%.1f", weightOfWires) + " KG");
    }


}