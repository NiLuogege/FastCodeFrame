package com.niluogege.example.commonsdk.base.mvp;


import com.niluogege.example.commonsdk.utils.Preconditions;

/**
 * Created by niluogege on 2018/8/23.
 * <p>
 * 基类 Presenter
 */

public class BasePresenter<V extends IView, M extends IModel> implements IPresenter {
    protected V mView;
    protected M mModel;

    public BasePresenter() {
        onStart();
    }

    public BasePresenter(V view) {
        Preconditions.checkNotNull(view, "%s cannot be null", IView.class.getName());
        this.mView = view;
        onStart();
    }

    public BasePresenter(V view, M model) {
        Preconditions.checkNotNull(view, "%s cannot be null", IView.class.getName());
        Preconditions.checkNotNull(model, "%s cannot be null", IModel.class.getName());
        this.mView = view;
        this.mModel = model;
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestory() {
        if (mModel !=null) mModel.onDestroy();
    }
}
