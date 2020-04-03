package com.example.gitusers;

import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;

import android.content.Intent;

import android.util.Log;

import android.view.View;
import android.widget.TextView;
import java.util.List;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;



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

        setAnimation(holder.itemView, position);
    }




    long DURATION = 300;
    private boolean on_attach = true;

    private void setAnimation(View itemView, int i) {
        if(!on_attach){
            i = -1;
        }
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION / 3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
              //  Log.d("myLogs", "onScrollStateChanged: Called " + newState);
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        super.onAttachedToRecyclerView(recyclerView);
    }
}




