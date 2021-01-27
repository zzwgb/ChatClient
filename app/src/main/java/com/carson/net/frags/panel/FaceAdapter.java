package com.carson.net.frags.panel;

import android.view.View;

import com.carson.common.widget.recycler.RecyclerAdapter;
import com.carson.face.Face;
import com.carson.net.R;

import java.util.List;

/**
 * Author: Create by Carson on 2021/1/21
 */
public class FaceAdapter extends RecyclerAdapter<Face.Bean> {

    public FaceAdapter(List<Face.Bean> beans, AdapterListener<Face.Bean> listener) {
        super(beans, listener);
    }

    @Override
    protected int getItemViewType(int position, Face.Bean bean) {
        return R.layout.cell_face;
    }

    @Override
    protected ViewHolder<Face.Bean> onCreateViewHolder(View root, int viewType) {
        return new FaceHolder(root);
    }
}