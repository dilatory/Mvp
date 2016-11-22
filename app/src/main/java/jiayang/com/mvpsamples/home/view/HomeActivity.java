package jiayang.com.mvpsamples.home.view;

//以子模块结构架构

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jiayang.com.mvpsamples.R;
import jiayang.com.mvpsamples.home.contact.HomeContact;
import jiayang.com.mvpsamples.home.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeContact.View, AdapterView.OnItemClickListener {

    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    private HomePresenter presenter;
    private ArrayAdapter adapter;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        datas = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        progressBar.setVisibility(View.VISIBLE);

        new HomePresenter(this, this);
        presenter.start();
    }

    @Override
    public void updateListView(List<String> datas) {
        progressBar.setVisibility(View.GONE);
        this.datas.clear();
        this.datas.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(HomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        presenter.onItemClick(i);
    }
}
