package com.codetrade.app.ui.Util.advance_adapter.selection;


public interface SelectionActionPerformer<E extends AbstractSelectableAdapter.Selectable> {

    E getItem(int index);

    void select(int index);

    void select(E item);

    void deSelect(int index);

    void deSelect(E item);

    @AbstractSelectableAdapter.SelectionMode
    int getSelectionMode();
}
