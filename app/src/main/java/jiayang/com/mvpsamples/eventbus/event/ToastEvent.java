package jiayang.com.mvpsamples.eventbus.event;

/**
 * Created by xiangkai on 2016/11/22.
 */

public class ToastEvent {
    private String msg;

    public ToastEvent(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
}
