package com.niluogege.example.commonsdk.network;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.niluogege.example.commonsdk.R;
import com.niluogege.example.commonsdk.utils.DialogUtils;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by niluogege on 2018/8/24.
 */

public class ProgressHelper {


    private static DialogPlus progress;

    private static DialogPlus createProgress(Context context) {

        DialogPlus plus = DialogUtils.createCustomDialog(context,
                R.layout.dialog_loading_layout,
                android.R.color.transparent,
                android.R.color.transparent,
                false, null, null);

        View image = plus.findViewById(R.id.img);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        image.startAnimation(animation);

        return plus;
    }


    public static <T> ObservableTransformer<T, T> applyProgressBar(Context context) {
        ObservableTransformer transformer = new ObservableTransformer<T, T>() {

            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Logger.e("doOnSubscribe");
                        if (progress == null)
                            progress = createProgress(context);
                        if (!progress.isShowing())
                            progress.show();
                    }
                }).doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Logger.e("doFinally");
                        if (progress != null && progress.isShowing())
                            progress.dismiss();
                    }
                });
            }
        };

        return transformer;
    }
}
