package com.codetrade.app.ui.Util.advance_adapter.selection_filter;


public interface FilterableSelectionActionPerformer<E extends FilterableAdapter.Selectable> {

    E getItem(int index);

    void select(int index);

    void select(E item);

    void deSelect(int index);

    void deSelect(E item);

    @FilterableAdapter.SelectionMode
    int getSelectionMode();
}
