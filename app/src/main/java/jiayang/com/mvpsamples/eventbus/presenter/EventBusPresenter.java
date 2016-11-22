package jiayang.com.mvpsamples.eventbus.presenter;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import jiayang.com.mvpsamples.R;
import jiayang.com.mvpsamples.eventbus.contact.EventBusContact;
import jiayang.com.mvpsamples.eventbus.event.DataEvent;
import jiayang.com.mvpsamples.eventbus.event.ToastEvent;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class EventBusPresenter implements EventBusContact.Presenter {
    private Context context;
    private EventBusContact.View view;

    public EventBusPresenter(Context context, EventBusContact.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadDatas() {
        String[] arrays = context.getResources().getStringArray(R.array.countries);
        final List<String> datas = new ArrayList<>();
        for (String array : arrays) {
            datas.add(array);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new DataEvent(datas));
            }
        }, 2000);
    }

    @Override
    public void onItemClick(String msg) {
        EventBus.getDefault().post(new ToastEvent(msg));
    }

    @Override
    public void start() {
        loadDatas();
    }
}
