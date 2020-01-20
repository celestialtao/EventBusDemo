package com.example.eventbus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by EventBus
 * User: CeletialTao
 * Date: 2020/1/19
 * Time: 13:59
 */
public class RightFragment extends Fragment {
    private static final String TAG ="RightFragment" ;

    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, null);
        mTextView = view.findViewById(R.id.tv);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    /**
     *  运行在主线程中的回调,可直接操作UI
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent1 msg) {
        String content = msg.getMsg()
                + "  threadName:  " + Thread.currentThread().getName()
                + "  threadId:  " + Thread.currentThread().getId();
        Log.d(TAG, "onEventMainThread(MessageEvent1 msg) 收到" + content);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent2 msg) {
        String content = msg.getMsg()
                + "  threadName:  " + Thread.currentThread().getName()
                + "  threadId:  " + Thread.currentThread().getId();
        Log.d(TAG, "onEventMainThread(MessageEvent2 msg) 收到" + content);
    }

    /**
     * 与发布者在同一个线程中,为默认的线程模式，
     * 用于处理简短的耗时操作
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(MessageEvent1 msg) {
        String content = msg.getMsg()
                + "  threadName: " + Thread.currentThread().getName()
                + "  threadId: " + Thread.currentThread().getId();
        Log.d(TAG, "OnEvent(MessageEvent1 msg) 收到" + content);
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(MessageEvent2 msg) {
        String content = msg.getMsg()
                + "  threadName: " + Thread.currentThread().getName()
                + "  threadId: " + Thread.currentThread().getId();
        Log.d(TAG, "OnEvent(MessageEvent2 msg) 收到" + content);
    }

    /**
     * 异步执行，即执行在一个新的线程
     * 适用于多个线程处理，内部有线程池管理
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAysnc(MessageEvent1 msg) {
        String content = msg.getMsg()
                + "  threadName: " + Thread.currentThread().getName()
                + "  threadId: " + Thread.currentThread().getId();
        Log.d(TAG, "onEventAysnc(MessageEvent1 msg) 收到" + content);
    }


    /**
     *  执行在子线程，如果发布者在子线程则直接执行，否则创建一个新线程
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackground(MessageEvent1 msg) {
        String content = msg.getMsg()
                + "  threadName: " + Thread.currentThread().getName()
                + "  threadId: " + Thread.currentThread().getId();
        Log.d(TAG, "onEventBackground(MessageEvent1 msg) 收到" + content);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
