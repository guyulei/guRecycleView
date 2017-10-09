package com.guyulei.gurecyclerview;

import android.view.View;

/**
 * Created by A on 2017/10/9.
 */

public interface OnItemClickListener {

    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}
