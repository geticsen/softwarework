package com.example.clwury.communication;

public class MsgJson {
    private String status;
    private String msg;
    public void setStatus(String status){
        this.status=status;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
    public String getStatus(){
        return status;
    }
    public String getMsg(){
        return msg;
    }
    private class data{
        private String id;
        private String msg;
        private String date;
        public void setId(String id){
            this.id=id;
        }
        public void setMsg(String msg){
            this.msg=msg;
        }
        public void setDate(String date){
            this.date=date;
        }
    }


}
