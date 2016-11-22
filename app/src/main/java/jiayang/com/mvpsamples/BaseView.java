package jiayang.com.mvpsamples;

/**
 * Created by xiangkai on 2016/11/21.
 */

public interface BaseView<T> {
    //声明泛型，在不同的presenter中复写为不同的presenter
    void setPresenter(T presenter);
}
