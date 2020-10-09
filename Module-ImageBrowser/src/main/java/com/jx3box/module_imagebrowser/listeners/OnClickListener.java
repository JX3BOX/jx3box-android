package com.jx3box.module_imagebrowser.listeners;

import android.view.View;

import androidx.fragment.app.FragmentActivity;

/**
 * author : maning
 * time   : 2018/04/10
 * desc   : 点击监听
 * version: 1.0
 */
public interface OnClickListener {

    void onClick(FragmentActivity activity, View view, int position, String url);

}
