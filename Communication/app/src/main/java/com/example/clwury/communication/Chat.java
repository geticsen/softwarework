package com.example.clwury.communication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Chat extends AppCompatActivity {
    private List<Msg> msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmessage);
        getData();
        Intent intent=getIntent();
        id=intent.getStringExtra("Id");
        //initMsgs();
        inputText=(EditText) findViewById(R.id.input);
        send =(Button) findViewById(R.id.send);
        msgRecyclerView=(RecyclerView) findViewById(R.id.msg_recycle_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String context=inputText.getText().toString();
                if(!context.equals("")){
                    //发送
                    sentMsg(context);
                    adapter.notifyItemInserted(msgList.size()-1);//当有新消息时，刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size()-1);//将RecyclerView定位到最后一行
                    inputText.setText("");//清空输入框中的内容
                }
            }
        });

    }
    private void initMsgs(){
        receiveMsg("3153");
        sentMsg("hjuhujgj");
    }

    public void receiveMsg(String msg) {
        //接收到的消息
        //列表刷新
        Msg msg3=new Msg(msg,Msg.Type_receive);
        msgList.add(msg3);


    }
    ///接受消息  http://www.senlear.com/chat/chat_send.php?userid=1000&touserid=1001&callbak=callbak

    public void sentMsg(String msg) {
        //列表刷新
        Msg msg3=new Msg(msg,Msg.Type_sent);
        msgList.add(msg3);
        //网络发送
        String url="http://www.senlear.com/chat/chat_receive.php?userid=1000&touserid=1001&message="+URLEncoder.encode(msg)+"&callbak=callbak";
        try{
            URL url1=new URL(url);
            HttpURLConnection con=(HttpURLConnection)url1.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            int responseCode= con.getResponseCode();
            if(responseCode==200){
                Toast.makeText(this,"发送成功！！！",Toast.LENGTH_SHORT).show();
                //添加到数据库，提示发送成功

            }else{
                Toast.makeText(this,"发送失败！！！",Toast.LENGTH_SHORT).show();
            }
            //con.disconnect();
        }catch (Exception e){
            Toast.makeText(this,"网络故障！！！",Toast.LENGTH_SHORT).show();
        }


    }
    public void getData() {
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
        String url = "http://www.senlear.com/chat/chat_send.php?userid=1000&touserid=1001&callbak=callbak";//json地址
        try{
            URL url1=new URL(url);
            HttpURLConnection con=(HttpURLConnection)url1.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            int responseCode= con.getResponseCode();
            if(responseCode==200) {
                    is = con.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    StringBuffer sbf = new StringBuffer();
                    String temp = null;
                    while ((temp = br.readLine()) != null) {
                        sbf.append(temp);
                        sbf.append("\r\n");
                    }
                    result =JSONPParser.parseJSONPStr(sbf.toString());
                    Msg msg3=new Msg(JSONPParser.parseJSONObject(result).get("data").toString(),Msg.Type_receive);
                    msgList.add(msg3);

            }else {
                Toast.makeText(this,con.getResponseCode(),Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        //
    }
}
