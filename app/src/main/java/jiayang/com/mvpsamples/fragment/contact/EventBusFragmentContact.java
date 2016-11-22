package jiayang.com.mvpsamples.fragment.contact;

import jiayang.com.mvpsamples.BasePresenter;
import jiayang.com.mvpsamples.BaseView;
import jiayang.com.mvpsamples.fragment.presenter.EventBusFragmentPresenter;

/**
 * Created by xiangkai on 2016/11/22.
 */

public interface EventBusFragmentContact {
    interface View extends BaseView<EventBusFragmentPresenter> {

    }

    interface Presenter extends BasePresenter {
        void loadDatas();

        void onItemClick(String msg);
    }
}
