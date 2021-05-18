package com.codetrade.app.ui.Util.advance_adapter;

import androidx.annotation.UiThread;

public interface OnSelectionChangeListener<T> {

    @UiThread
    void onSelectionChange(T t, boolean isSelected);
}
