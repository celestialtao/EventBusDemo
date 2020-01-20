package com.example.eventbus;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by EventBus
 * User: CeletialTao
 * Date: 2020/1/19
 * Time: 13:59
 */
public class LeftFragment extends ListFragment {
    private static final String TAG ="LeftFragment" ;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] strs = {"主线程消息1", "子线程消息1", "主线程消息2"};
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strs));
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        switch (position){
            case 0:
                //主线程消息1
                Log.d(TAG, ">>>>主线程消息1  " + "threadName:" + Thread.currentThread().getName()
                        + "  threadId:" + Thread.currentThread().getId());
                EventBus.getDefault().post(new MessageEvent1("主线程发送的消息1"));
                break;
            case 1:
                //子线程消息1
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, ">>>>子线程消息1  " + "threadName:" + Thread.currentThread().getName()
                                + "  threadId:" + Thread.currentThread().getId());
                        EventBus.getDefault().post(new MessageEvent1("子线程发送的消息1"));
                    }
                }).start();
                break;
            case 2:
                //主线程消息2
                Log.d(TAG, ">>>>主线程消息2  " + "threadName:" + Thread.currentThread().getName()
                        + "  threadId:" + Thread.currentThread().getId());
                EventBus.getDefault().post(new MessageEvent2("主线程发送的消息2"));
                break;
        }
    }
}
