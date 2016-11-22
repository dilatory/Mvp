package jiayang.com.mvpsamples.loginoptimize.contact;

import android.widget.TextView;

import jiayang.com.mvpsamples.BasePresenter;
import jiayang.com.mvpsamples.BaseView;
import jiayang.com.mvpsamples.login.presenter.LoginPresenter;
import jiayang.com.mvpsamples.loginoptimize.presenter.LoginOptimizePresenter;

/**
 * Created by xiangkai on 2016/11/22.
 */

public interface LoginOptimizeContact {
    interface View extends BaseView<LoginOptimizePresenter> {
        void loginResult(String result);

        void clearEditText(TextView tvUsername, TextView tvPassword);
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);

        void clear(TextView tvUsername, TextView tvPassword);

        void onDestory();
    }
}
