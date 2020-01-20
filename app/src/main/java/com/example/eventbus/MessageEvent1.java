package com.example.eventbus;

/**
 * Created by EventBus
 * User: CeletialTao
 * Date: 2020/1/19
 * Time: 13:16
 */
public class MessageEvent1 {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MessageEvent1(String msg) {
        this.msg = msg;
    }
}
