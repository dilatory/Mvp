package jiayang.com.mvpsamples.fragment.presenter;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import jiayang.com.mvpsamples.R;
import jiayang.com.mvpsamples.eventbus.event.DataEvent;
import jiayang.com.mvpsamples.eventbus.event.ToastEvent;
import jiayang.com.mvpsamples.fragment.contact.EventBusFragmentContact;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class EventBusFragmentPresenter implements EventBusFragmentContact.Presenter {
    private Context context;
    private EventBusFragmentContact.View view;

    public EventBusFragmentPresenter(Context context, EventBusFragmentContact.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadDatas() {
        final List<String> datas = new ArrayList<>();
        String[] arrays = context.getResources().getStringArray(R.array.countries);
        for (int i = 0; i < arrays.length; i++) {
            datas.add(arrays[i]);
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
