package com.shivamgarg.trashit.helperClasses.userAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shivamgarg.trashit.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProgressAdapterClass extends RecyclerView.Adapter<ProgressAdapterClass.ViewHolder> {

    private List<ProgressDataClass> progressData;
    private ProgressCardListener listner;

    public ProgressAdapterClass(List<ProgressDataClass> userOrderInProgress) {
        this.progressData=userOrderInProgress;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_in_progress,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String dateSource=progressData.get(position).getDate();
        String weightSource=progressData.get(position).getWeight();
        holder.setData(dateSource,weightSource);
    }

    @Override
    public int getItemCount() {
        return progressData.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView dateView;
        private TextView weightView;

        public ViewHolder(View itemView) {
            super(itemView);
           weightView=itemView.findViewById(R.id.progress_card_weight);
           dateView=itemView.findViewById(R.id.progress_card_date);
        }


        @Override
        public void onClick(View v) {

        }

        public void setData(String dateSource, String weightSource) {
            weightView.setText(weightSource);
            dateView.setText(dateSource);
        }
    }

    public interface ProgressCardListener{
        public void onClick(View v,int position);
    }

}
