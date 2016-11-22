package jiayang.com.mvpsamples.home.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import jiayang.com.mvpsamples.fragment.view.EventBusFragmentActivity;
import jiayang.com.mvpsamples.home.contact.HomeContact;
import jiayang.com.mvpsamples.home.view.EventBusActivity;
import jiayang.com.mvpsamples.login.view.LoginActivity;
import jiayang.com.mvpsamples.login.view.LoginOptimizeActivity;
import jiayang.com.mvpsamples.outteradapter.view.AdapterActivity;

/**
 * Created by xiangkai on 2016/11/21.
 */

public class HomePresenter implements HomeContact.Presenter {
    private Context context;
    private HomeContact.View view;

    public HomePresenter(Context context, HomeContact.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadDatas();
    }

    @Override
    public void loadDatas() {
        final List<String> datas = new ArrayList<>();
        datas.add("MVP with Login Showcase");
        datas.add("Optimized MVP with Login Showcase");
        datas.add("MVP in Adapter A");
        datas.add("MVP with EventBus");
        datas.add("MVP with EventBus in Fragments");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.updateListView(datas);
            }
        }, 2000);
    }

    @Override
    public void onItemClick(int position) {
        Class<?>[] activity = {LoginActivity.class, LoginOptimizeActivity.class,
                AdapterActivity.class, EventBusActivity.class, EventBusFragmentActivity.class};
        Intent intent = new Intent(context, activity[position]);
        context.startActivity(intent);
    }
}
