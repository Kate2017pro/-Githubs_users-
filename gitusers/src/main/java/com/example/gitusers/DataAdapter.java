package com.example.gitusers;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.util.Log;

import android.view.View;

import android.widget.TextView;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<UsersClass> users;
    Activity cont;

    public DataAdapter (List<UsersClass> user,Activity context){
        this.users=user;
        this.cont=context;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{
        TextView us_id;
        TextView log_us;
        CardView card;
        public DataViewHolder(View view){
            super(view);
            us_id=view.findViewById((R.id.user_id));
            log_us=view.findViewById(R.id.name);
            card=view.findViewById(R.id.card_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("my logs","Hажато "+v.getTag().toString());
                    Intent intent = new Intent(cont, Detail_information.class);
                    intent.putExtra("name_user", v.getTag().toString());
                    cont.startActivity(intent);
                }
            });

        }
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return users.size();
    }

    @Override
    public void onBindViewHolder (@NonNull DataViewHolder holder, int position){
        holder.us_id.setText(this.users.get(position).getId());
        holder.log_us.setText(this.users.get(position).getLogin());
        holder.itemView.setTag(this.users.get(position).getLogin());
    }

}




