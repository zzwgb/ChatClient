package com.carson.net.frags.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.carson.factory.model.Author;
import com.carson.net.R;
import com.carson.net.activities.AccountActivity;

public class MessageActivity extends AppCompatActivity {

    public static void show(Context context, Author author) {
        context.startActivity(new Intent(context, MessageActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }
}