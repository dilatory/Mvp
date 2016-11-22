package jiayang.com.mvpsamples.loginoptimize.presenter;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import jiayang.com.mvpsamples.login.contact.LoginContact;
import jiayang.com.mvpsamples.loginoptimize.contact.LoginOptimizeContact;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class LoginOptimizePresenter implements LoginOptimizeContact.Presenter {
    private static final String USERNAME = "mvp";
    private static final String PASSWROD = "mvp";
    private Context context;
    private LoginOptimizeContact.View view;

    public LoginOptimizePresenter(Context context, LoginOptimizeContact.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void login(final String username, final String password) {
        final String result;
        if (USERNAME.equals(username) && PASSWROD.equals(password)) {
            result = "Login success";
        } else {
            result = "Login fail";
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view != null) {
                    view.loginResult(result);
                }
            }
        }, 2000);
    }

    @Override
    public void clear(TextView tvUsername, TextView tvPassword) {
        view.clearEditText(tvUsername, tvPassword);
    }

    @Override
    public void onDestory() {
        view = null;
    }

    @Override
    public void start() {

    }
}
