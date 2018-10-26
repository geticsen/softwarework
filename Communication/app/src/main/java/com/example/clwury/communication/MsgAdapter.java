package com.example.clwury.communication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftlayout;
        LinearLayout rightlayout;
        TextView leftMsg;
        TextView rightMsg;
        public ViewHolder(View view){
            super(view);
            leftlayout=(LinearLayout) view.findViewById(R.id.left_layout);
            rightlayout=(LinearLayout) view.findViewById(R.id.right_layout);
            leftMsg=(TextView) view.findViewById(R.id.left_msg);
            rightMsg=(TextView) view.findViewById(R.id.right_msg);
        }
    }
    public MsgAdapter(List<Msg> msgList){
        mMsgList=msgList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }
    public void onBindViewHolder(ViewHolder holder,int position){
        Msg msg=mMsgList.get(position);
        if(msg.getType()==Msg.Type_receive){
            //如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
            holder.leftlayout.setVisibility(View.VISIBLE);
            holder.rightlayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        }else if(msg.getType()==Msg.Type_sent){
            //如果是发出的消息，则显示右边的消息布局，将左边的消息布局隐藏
            holder.leftlayout.setVisibility(View.GONE);
            holder.rightlayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());
        }
    }
    public int getItemCount(){
        return mMsgList.size();
    }
}
