package jiayang.com.mvpsamples.eventbus.contact;

import jiayang.com.mvpsamples.BasePresenter;
import jiayang.com.mvpsamples.BaseView;
import jiayang.com.mvpsamples.eventbus.presenter.EventBusPresenter;

/**
 * Created by xiangkai on 2016/11/22.
 */

public interface EventBusContact {
    interface View extends BaseView<EventBusPresenter> {
    }
    interface Presenter extends BasePresenter {
        void loadDatas();

        void onItemClick(String msg);
    }
}
