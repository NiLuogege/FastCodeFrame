package com.niluogege.example.commonsdk.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niluogege.example.commonsdk.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * Created by niluogege on 2018/8/24.
 * <p>
 * Dialog 帮助类
 */

public class DialogUtils {

    /**
     * @param context
     * @param layoutId         layoutID
     * @param contentBgColorId 内容背景色
     * @param overlayBgColorId window背景色
     * @param isCancelable     是否点击内容之外dismis dialog
     * @param onClickListener  点击事件监听
     * @param dismissListener  dismiss事件
     * @return
     */
    public static DialogPlus createCustomDialog(Context context,
                                                int layoutId,
                                                int contentBgColorId,
                                                int overlayBgColorId,
                                                boolean isCancelable,
                                                OnClickListener onClickListener,
                                                OnDismissListener dismissListener) {
        View customView = LayoutInflater.from(context.getApplicationContext()).inflate(layoutId, null);
        DialogPlus dialogPlus = DialogPlus.newDialog(context)
                .setContentHolder(new ViewHolder(customView))
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setContentBackgroundResource(contentBgColorId)
                .setOverlayBackgroundResource(overlayBgColorId)
                .setGravity(Gravity.CENTER)
                .setOnClickListener(onClickListener)
                .setCancelable(isCancelable)
                .setOnDismissListener(dismissListener)
                .create();
        return dialogPlus;
    }

    public static DialogPlus createCommonDialog(Context context, int layoutId, OnClickListener onClickListener) {
        return createCustomDialog(context, layoutId, android.R.color.white, R.color.public_dialog_bg_color, true, onClickListener, null);
    }

    public static DialogPlus createCommonDialog(Context context, int layoutId, OnClickListener onClickListener, OnDismissListener dismissListener) {
        return createCustomDialog(context, layoutId, android.R.color.white, R.color.public_dialog_bg_color, true, onClickListener, dismissListener);
    }

    public static DialogPlus createCommonDialog(Context context, int layoutId, boolean cancelable, OnClickListener onClickListener) {
        return createCustomDialog(context, layoutId, android.R.color.white, R.color.public_dialog_bg_color, cancelable, onClickListener, null);
    }

    public static DialogPlus createCommonDialog(Context context, int layoutId, boolean cancelable, OnClickListener onClickListener, OnDismissListener dismissListener) {
        return createCustomDialog(context, layoutId, android.R.color.white, R.color.public_dialog_bg_color, cancelable, onClickListener, dismissListener);
    }


}