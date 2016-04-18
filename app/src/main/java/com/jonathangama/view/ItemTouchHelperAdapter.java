package com.jonathangama.view;
/**
 * Created by Jonathan Gama on 4/11/2016.
 */

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
