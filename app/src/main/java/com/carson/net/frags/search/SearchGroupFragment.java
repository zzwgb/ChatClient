package com.carson.net.frags.search;


import com.carson.common.app.Fragment;
import com.carson.net.R;
import com.carson.net.activities.SearchActivity;

/**
 * 搜索群
 */
public class SearchGroupFragment extends Fragment implements SearchActivity.SearchFragment {

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_group;
    }

    @Override
    public void search(String content) {

    }
}