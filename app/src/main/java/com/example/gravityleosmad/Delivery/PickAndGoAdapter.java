package com.example.gravityleosmad.Delivery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gravityleosmad.R;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class PickAndGoAdapter extends RecyclerView.Adapter<PickAndGoAdapter.MyViewHolder>{
    Context cont;
    ArrayList<PickAndGoModel> list;
    private static RecyclerViewClickListner listner;
    public PickAndGoAdapter(Context cont, ArrayList<PickAndGoModel> list, RecyclerViewClickListner listner) {
        this.listner = listner;
        this.cont = cont;
        this.list = list;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cont).inflate(R.layout.pickandgo_singleview,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull PickAndGoAdapter.MyViewHolder holder, @SuppressLint("RecyclerView1") int position) {

        PickAndGoModel ob = list.get(position);
        holder.Pick_Up_Date.setText(ob.getPick_Up_Date());
        holder.Pick_Up_Time.setText(ob.getPick_Up_Time());
        holder.Pick_Up_Location.setText(ob.getPick_Up_Location());
        holder.Number.setText(ob.getNumber());
        //holder.purl.setText(ob.getPurl());
        //Glide.with(holder.propic.getContext()).load(ob.getPurl()).into(holder.propic);


    }
    //Get item count
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView  Pick_Up_Date, Pick_Up_Time, Pick_Up_Location, Number;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            Pick_Up_Date = itemView.findViewById(R.id.textview11);
            Pick_Up_Time = itemView.findViewById(R.id.textView22);
            Pick_Up_Location = itemView.findViewById(R.id.textView33);
            Number= itemView.findViewById(R.id.textView44);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listner.onClick(v,getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);

    }
}