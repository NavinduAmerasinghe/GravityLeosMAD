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

public class CashOnDeliveryAdapter extends RecyclerView.Adapter<CashOnDeliveryAdapter.MyViewHolder>{
    Context cont;
    ArrayList<CashOnDeliveryModel> list;
    private static RecyclerViewClickListner listner;
    public CashOnDeliveryAdapter(Context cont, ArrayList<CashOnDeliveryModel> list, RecyclerViewClickListner listner) {
        this.listner = listner;
        this.cont = cont;
        this.list = list;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cont).inflate(R.layout.cashondelivery_singleview,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull CashOnDeliveryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        CashOnDeliveryModel ob = list.get(position);
        holder.House_Address.setText(ob.getHouse_Address());
        holder.Street.setText(ob.getStreet());
        holder.City.setText(ob.getCity());
        holder.Phone_Number.setText(ob.getPhone_Number());
        holder.Email.setText(ob.getEmail());
        //holder.purl.setText(ob.getPurl());
        //Glide.with(holder.propic.getContext()).load(ob.getPurl()).into(holder.propic);


    }
    //Get item count
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView  House_Address, Street, City, Phone_Number, Email;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            House_Address = itemView.findViewById(R.id.textview1);
            Street = itemView.findViewById(R.id.textView2);
            City = itemView.findViewById(R.id.textView3);
            Phone_Number= itemView.findViewById(R.id.textView4);
            Email = itemView.findViewById(R.id.textView5);
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