package com.example.clwury.communication;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class friendListAdapter extends RecyclerView.Adapter<friendListAdapter.ViewHolder> {
    private List<friendListItem> mMsgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View friendview;
        TextView titleuser;
        TextView usermsg;
        public ViewHolder(View view){
            super(view);
            friendview=view;
            titleuser=(TextView) view.findViewById(R.id.titleuser);
            usermsg=(TextView) view.findViewById(R.id.usermsg);
        }
    }
    public friendListAdapter(List<friendListItem> msgList){
        mMsgList=msgList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.friendview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //获得点击item值
                /*int position=holder.getAdapterPosition();
                friendListItem friend=mMsgList.get(position);*/

                Intent intent=new Intent(v.getContext(),Chat.class);
                intent.putExtra("Id","1001");

                v.getContext().startActivity(intent);

            }
        });
        return new ViewHolder(view);
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        friendListItem Item=mMsgList.get(position);

        holder.titleuser.setText(Item.getTitile());
        holder.usermsg.setText(Item.getMsg());
    }
    public int getItemCount(){
        return mMsgList.size();
    }
}
class  friendListItem{
    private  String titile;
    private  String msg;
    public friendListItem(String titile,String msg){
        this.msg=msg;
        this.titile=titile;
    }
    public String getTitile(){
        return titile;
    }
    public String getMsg(){
        return msg;
    }

}