package jiayang.com.mvpsamples.login.contact;

import android.widget.TextView;

import org.w3c.dom.Text;

import jiayang.com.mvpsamples.BasePresenter;
import jiayang.com.mvpsamples.BaseView;
import jiayang.com.mvpsamples.login.presenter.LoginPresenter;

/**
 * Created by xiangkai on 2016/11/22.
 */

public interface LoginContact {
    interface View extends BaseView<LoginPresenter> {
        void loginResult(String result);

        void clearEditText(TextView tvUsername, TextView tvPassword);
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);

        void clear(TextView tvUsername, TextView tvPassword);
    }
}
