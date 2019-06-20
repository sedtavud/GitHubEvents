package com.example.com01.githubevents;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com01.githubevents.Model.EventsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private List<EventsResponse> mData;
    private OnItemClickListener mClickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener){
        mClickListener = clickListener;
    }

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public EventsAdapter(List<EventsResponse> data){
        mData = data;
    }
    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.id.setText(String.valueOf(mData.get(i).getId()));
        viewHolder.login.setText(mData.get(i).getActor().getLogin());
//        viewHolder.display_login.setText(mData.get(i).getActor().getDisplay_login());
//        viewHolder.gravatar_id.setText(mData.get(i).getActor().getGravatar_id());
//        viewHolder.url.setText(mData.get(i).getActor().getUrl());
        if (mData.get(i).getActor().getAvatar_url()!=null){
            Picasso.get().load(mData.get(i).getActor().getAvatar_url()).into(viewHolder.avatar);
        }else{
            viewHolder.avatar.setImageResource(R.drawable.avatar);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id,login,display_login,gravatar_id,url;
        ImageView avatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            login = itemView.findViewById(R.id.login);
//            display_login = itemView.findViewById(R.id.display_login);
//            gravatar_id = itemView.findViewById(R.id.gravatar_id);
//            url = itemView.findViewById(R.id.url);
            avatar = itemView.findViewById(R.id.avatar_url);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }


}
