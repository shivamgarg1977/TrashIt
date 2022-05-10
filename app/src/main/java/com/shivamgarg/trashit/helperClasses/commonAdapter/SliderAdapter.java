package com.shivamgarg.trashit.helperClasses.commonAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.shivamgarg.trashit.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(@NonNull Context context) {
        this.context=context;
    }
    int images[]={
            R.drawable.sell,
            R.drawable.recycle,
            R.drawable.earn,
            R.drawable.more_to_earn,
            R.drawable.rewards
    };
    String headings[]={
            "SELL",
            "RECYCLE",
            "EARN",
            "THERE IS MORE TO EARN",
            "GET REWARDS"
    };
    String descriptions[]={
            "Now no more need to wait for the vendors to come to sell trash in just a few steps",
            "Sell your home, industrial or office trash and in recycling the waste",
            "Earn easily now by selling trash...",
            "Sell trashes from the app and earn environmental points",
            "Redeem your points into a plant gift from us. One step towards green environment"
    };


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.onboarding_viewpager_slider,container,false);
        ImageView imageView=view.findViewById(R.id.imageView);
        TextView heading=view.findViewById(R.id.viewpager_heading);
        TextView description=view.findViewById(R.id.viewpager_description);
        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        description.setText(descriptions[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }

}
