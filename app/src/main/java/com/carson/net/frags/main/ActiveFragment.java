package com.carson.net.frags.main;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carson.common.app.Fragment;
import com.carson.common.widget.GalleryView;
import com.carson.net.R;

import butterknife.BindView;


public class ActiveFragment extends Fragment {

    /*@BindView(R.id.galleryView)
    GalleryView mGalleryView;*/

    public ActiveFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

    @Override
    protected void initData() {
        super.initData();
        /*mGalleryView.setup(getLoaderManager(), new GalleryView.SelectedChangeListener() {
            @Override
            public void onSelectedCountChanged(int count) {

            }
        });*/
    }
}