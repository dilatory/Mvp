package jiayang.com.mvpsamples.outteradapter.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jiayang.com.mvpsamples.outteradapter.contact.AdapterContact;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class AdapterPresenter implements AdapterContact.Presenter {
    private Context context;
    private AdapterContact.View view;

    public AdapterPresenter(Context context, AdapterContact.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadDatas() {
        final List<String> datas = new ArrayList<>();
        datas.add("jiayang");
        datas.add("Loves");
        datas.add("Nuonuo");
        datas.add("Deeply");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.updateListView(datas);
            }
        }, 2000);
    }

    @Override
    public void onItemClick(String itemStr) {
        view.itemClickResult(itemStr);
    }

    @Override
    public void add(final List<String> datas, final int position) {
        StringBuilder builder = new StringBuilder(datas.get(position));
        datas.add(builder.append("new").toString());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.updateListView(datas);
            }
        }, 2000);
    }

    @Override
    public void delete(final List<String> datas, final int position) {
        datas.remove(position);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.updateListView(datas);
            }
        }, 2000);
    }

    @Override
    public void start() {
        loadDatas();
    }
}
