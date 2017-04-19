package me.weyye.todaynews.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.weyye.todaynews.R;
import me.weyye.todaynews.base.BaseFragment;
import me.weyye.todaynews.utils.ConstanceValue;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class VideoFragment extends BasePagerFragment {
    private String[] titles = new String[]{"西游", "三国", "水浒", "红楼", "金瓶", "西游", "三国"};
    private String[] titlesCode = new String[]{"video", "subv_funny", "subv_voice", "subv_society", "subv_comedy", "subv_haoshengyin", "subv_entertainment"};


    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_video, null);
    }

    @Override
    protected String[] getTitles() {
        return titles;
    }

    @Override
    protected BaseFragment getFragment(int position) {

        return VideoFragment.newInstance(titlesCode[position]);
    }


    public static VideoListFragment newInstance(String code) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindViews(View view) {
        super.bindViews(view);
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }

    @Override
    protected void setListener() {

    }
}
