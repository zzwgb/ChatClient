package com.carson.net.frags.message;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carson.net.R;

/**
 * 群聊页面
 */
public class ChatGroupFragment extends ChatFragment {

    public ChatGroupFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_chat_group;
    }
}