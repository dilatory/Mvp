package jiayang.com.mvpsamples.login.presenter;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import jiayang.com.mvpsamples.login.contact.LoginContact;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class LoginPresenter implements LoginContact.Presenter {
    private static final String USERNAME = "mvp";
    private static final String PASSWROD = "mvp";
    private Context context;
    private LoginContact.View view;

    public LoginPresenter(Context context, LoginContact.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void login(final String username, final String password) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String result;
                if (USERNAME.equals(username) && PASSWROD.equals(password)) {
                    result = "Login success";
                } else {
                    result = "Login fail";
                }
                view.loginResult(result);
            }
        }, 2000);
    }

    @Override
    public void clear(TextView tvUsername, TextView tvPassword) {
        view.clearEditText(tvUsername, tvPassword);
    }

    @Override
    public void start() {

    }
}
