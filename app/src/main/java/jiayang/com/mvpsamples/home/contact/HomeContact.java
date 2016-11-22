package jiayang.com.mvpsamples.home.contact;

import java.util.List;

import jiayang.com.mvpsamples.BasePresenter;
import jiayang.com.mvpsamples.BaseView;
import jiayang.com.mvpsamples.home.presenter.HomePresenter;

/**
 * Created by xiangkai on 2016/11/21.
 */

public interface HomeContact {
    interface View extends BaseView<HomePresenter> {
        void updateListView(List<String> datas);
    }

    interface Presenter extends BasePresenter {
        void loadDatas();
        void onItemClick(int position);
    }
}
