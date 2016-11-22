package jiayang.com.mvpsamples.fragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import jiayang.com.mvpsamples.R;
import jiayang.com.mvpsamples.fragment.contact.EventBusFragmentContact;
import jiayang.com.mvpsamples.fragment.presenter.EventBusFragmentPresenter;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class EventBusFragmentActivity extends AppCompatActivity {
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    private VPAdapter adapter;
    private List<String> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_fragment);
        ButterKnife.bind(this);
//        隐藏actionbar
        getSupportActionBar().hide();
        datas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            datas.add("tab" + i);
        }
        adapter = new VPAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class VPAdapter extends FragmentPagerAdapter {

        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new DemoFragment();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return datas.get(position);
        }
    }
}
