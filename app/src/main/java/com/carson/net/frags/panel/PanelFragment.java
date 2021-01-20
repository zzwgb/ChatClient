package com.carson.net.frags.panel;


import com.carson.common.app.Fragment;
import com.carson.net.R;

/**
 * 底部面板实现
 */
public class PanelFragment extends Fragment {

    public PanelFragment() {
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_panel;
    }

    public void showFace() {
      /*  mRecordPanel.setVisibility(View.GONE);
        mGalleryPanel.setVisibility(View.GONE);
        mFacePanel.setVisibility(View.VISIBLE);*/
    }

    public void showRecord() {
        /*mRecordPanel.setVisibility(View.VISIBLE);
        mGalleryPanel.setVisibility(View.GONE);
        mFacePanel.setVisibility(View.GONE);*/
    }

    public void showGallery() {
        /*mRecordPanel.setVisibility(View.GONE);
        mGalleryPanel.setVisibility(View.VISIBLE);
        mFacePanel.setVisibility(View.GONE);*/
    }
}