package jiayang.com.mvpsamples.eventbus.event;

import java.util.List;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class DataEvent {
    private List<String> datas;

    public DataEvent(List<String> datas) {
        this.datas = datas;
    }
    public List<String> getDatas() {
        return datas;
    }
}
