package jiayang.com.mvpsamples.home.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import jiayang.com.mvpsamples.R;
import jiayang.com.mvpsamples.eventbus.contact.EventBusContact;
import jiayang.com.mvpsamples.eventbus.event.DataEvent;
import jiayang.com.mvpsamples.eventbus.event.ToastEvent;
import jiayang.com.mvpsamples.eventbus.presenter.EventBusPresenter;

/*eventbus使用
* register -> post -> onevent -> unregister
*/
public class EventBusActivity extends AppCompatActivity implements EventBusContact.View, AdapterView.OnItemClickListener {

    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    private EventBusPresenter presenter;
    private ArrayAdapter adapter;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        datas = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        progressBar.setVisibility(View.VISIBLE);

        new EventBusPresenter(this, this);
        presenter.start();
    }

    @Override
    public void setPresenter(EventBusPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        presenter.onItemClick(datas.get(i));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(ToastEvent toastEvent) {
        Toast.makeText(this, toastEvent.getMsg(), Toast.LENGTH_SHORT).show();
    }

    public void onEvent(DataEvent dataEvent) {
        progressBar.setVisibility(View.GONE);
        this.datas.clear();
        this.datas.addAll(dataEvent.getDatas());
        adapter.notifyDataSetChanged();
    }
}
