package jiayang.com.mvpsamples.outteradapter.contact;

import java.util.List;

import jiayang.com.mvpsamples.BasePresenter;
import jiayang.com.mvpsamples.BaseView;
import jiayang.com.mvpsamples.outteradapter.presenter.AdapterPresenter;

/**
 * Created by xiangkai on 2016/11/22.
 */

public interface AdapterContact {
    interface View extends BaseView<AdapterPresenter> {
        void updateListView(List<String> datas);

        void itemClickResult(String itemStr);
    }

    interface Presenter extends BasePresenter {
        void loadDatas();

        void onItemClick(String itemStr);

        void add(List<String> datas, int position);

        void delete(List<String> datas, int position);
    }
}
