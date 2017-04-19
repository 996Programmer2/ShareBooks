package me.weyye.todaynews.ui.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.todaynews.R;
import me.weyye.todaynews.base.BaseFragment;
import me.weyye.todaynews.base.BaseMvpFragment;
import me.weyye.todaynews.presenter.HomePresenter;
import me.weyye.todaynews.ui.activity.ChannelActivity;
import me.weyye.todaynews.ui.activity.SearchActivity;
import me.weyye.todaynews.ui.adapter.TitlePagerAdapter;
import me.weyye.todaynews.ui.view.colortrackview.ColorTrackTabLayout;
import me.weyye.todaynews.utils.CommonUtil;
import me.weyye.todaynews.view.IHomeView;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements IHomeView {
    @BindView(R.id.feed_top_search_hint)
    TextView feedTopSearchHint;
    @BindView(R.id.tab)
    ColorTrackTabLayout tab;
    @BindView(R.id.icon_category)
    ImageView iconCategory;
    @BindView(R.id.vp)
    ViewPager vp;
    private final String[] titles = new String[]{"aa", "bb", "cc", "dd", "ee", "ff", "aa", "bb", "cc", "dd", "ee", "ff", "aa", "bb", "cc", "dd", "ee", "ff", "aa", "bb", "cc"};
    private String[] titlesCode = new String[]{"__all__", "video", "news_hot", "news_society", "news_entertainment", "news_tech", "news_car", "news_sports", "news_finance", "news_military", "news_world", "news_fashion", "news_game", "news_travel", "news_history", "news_discovery", "news_food", "news_baby", "news_regimen", "news_story", "news_essay"};

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    protected void bindViews(View view) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void processLogic() {
        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            NewsListFragment fragment = NewsListFragment.newInstance(titlesCode[i]);
            fragments.add(fragment);
        }

        vp.setAdapter(new TitlePagerAdapter(getChildFragmentManager(), fragments, titles));
        vp.setOffscreenPageLimit(titles.length);

        tab.setTabPaddingLeftAndRight(CommonUtil.dip2px(10), CommonUtil.dip2px(10));
        tab.setupWithViewPager(vp);
        tab.post(new Runnable() {
            @Override
            public void run() {
                //设置最小宽度，使其可以在滑动一部分距离
                ViewGroup slidingTabStrip = (ViewGroup) tab.getChildAt(0);
                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth() + iconCategory.getMeasuredWidth());
            }
        });
        //隐藏指示器
        tab.setSelectedTabIndicatorHeight(0);


    }

    @Override
    protected void setListener() {

    }

    static final int REQUEST_CHANNEL = 111;

    @OnClick({R.id.feed_top_search_hint, R.id.icon_category})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_category:
//                intent2Activity(ChannelActivity.class);
                Intent intent = new Intent(mContext, ChannelActivity.class);
                startActivityForResult(intent, REQUEST_CHANNEL);
                break;
            case R.id.feed_top_search_hint:
                Intent searchIntent = new Intent(mContext, SearchActivity.class);
                startActivity(searchIntent);
        }
    }
}
