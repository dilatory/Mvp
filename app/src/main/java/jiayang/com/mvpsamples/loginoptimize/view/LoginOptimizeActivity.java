package jiayang.com.mvpsamples.login.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiayang.com.mvpsamples.R;
import jiayang.com.mvpsamples.loginoptimize.contact.LoginOptimizeContact;
import jiayang.com.mvpsamples.loginoptimize.presenter.LoginOptimizePresenter;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class LoginOptimizeActivity extends AppCompatActivity implements LoginOptimizeContact.View {
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.bt_clear)
    Button btClear;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private LoginOptimizePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        new LoginOptimizePresenter(this, this);
//        没有初始化界面时的业务逻辑时，则不调用start
//        presenter.start();
    }

    @OnClick({R.id.bt_login, R.id.bt_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                presenter.login(username, password);
                break;
            case R.id.bt_clear:
                presenter.clear(etUsername, etPassword);
                break;
        }
    }

    @Override
    public void loginResult(String result) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearEditText(TextView tvUsername, TextView tvPassword) {
        tvUsername.setText("");
        tvPassword.setText("");
    }

    @Override
    public void setPresenter(LoginOptimizePresenter presenter) {
        this.presenter = presenter;
    }

    //    避免内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
