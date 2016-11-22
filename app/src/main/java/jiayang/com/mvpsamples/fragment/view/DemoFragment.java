package jiayang.com.mvpsamples.fragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import jiayang.com.mvpsamples.R;
import jiayang.com.mvpsamples.eventbus.event.DataEvent;
import jiayang.com.mvpsamples.eventbus.event.ToastEvent;
import jiayang.com.mvpsamples.fragment.contact.EventBusFragmentContact;
import jiayang.com.mvpsamples.fragment.presenter.EventBusFragmentPresenter;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class DemoFragment extends Fragment implements EventBusFragmentContact.View, AdapterView.OnItemClickListener {
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    private List<String> datas;
    private EventBusFragmentPresenter presenter;
    private ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_demo, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        progressBar.setVisibility(View.VISIBLE);
        datas = new ArrayList<>();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        new EventBusFragmentPresenter(getActivity(), this);
        presenter.start();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setPresenter(EventBusFragmentPresenter presenter) {
        this.presenter = presenter;
    }

    public void onEvent(DataEvent dataEvent) {
        progressBar.setVisibility(View.INVISIBLE);
        datas.clear();
        datas.addAll(dataEvent.getDatas());
        adapter.notifyDataSetChanged();
    }

    public void onEvent(ToastEvent toastEvent) {
        Toast.makeText(getActivity(), toastEvent.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        presenter.onItemClick((String) adapter.getItem(i));
    }
}
