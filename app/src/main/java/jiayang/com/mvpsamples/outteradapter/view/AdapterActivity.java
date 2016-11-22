package jiayang.com.mvpsamples.outteradapter.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jiayang.com.mvpsamples.R;
import jiayang.com.mvpsamples.outteradapter.contact.AdapterContact;
import jiayang.com.mvpsamples.outteradapter.presenter.AdapterPresenter;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class AdapterActivity extends AppCompatActivity implements AdapterContact.View, AdapterView.OnItemClickListener {
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.list_view)
    ListView listView;

    private AdapterPresenter presenter;
    private CustomAdapter adapter;
    private List<String> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);
        datas = new ArrayList<>();
        adapter = new CustomAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        new AdapterPresenter(this, this);
        presenter.start();
    }

    @Override
    public void updateListView(List<String> datas) {
        progressBar.setVisibility(View.INVISIBLE);
//      点击add，调用clear后 会清除形参datas的值，巨大bug啊
//        this.datas.clear();

        this.datas = datas;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void itemClickResult(String itemStr) {
        Toast.makeText(this, itemStr, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(AdapterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        presenter.onItemClick(adapter.getItem(i));
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public String getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View convertView, ViewGroup viewGroup) {
            View view;
            ViewHolder holder;
            if (convertView == null) {
                view = LayoutInflater.from(AdapterActivity.this).inflate(R.layout.item_custom, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            holder.tvContent.setText(datas.get(i));
            holder.tvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.onItemClick(getItem(i));
                }
            });
            holder.tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    presenter.add(datas, i);
                }
            });
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    presenter.delete(datas, i);
                }
            });
            return view;
        }
    }

    class ViewHolder {
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_add)
        TextView tvAdd;
        @Bind(R.id.tv_delete)
        TextView tvDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
