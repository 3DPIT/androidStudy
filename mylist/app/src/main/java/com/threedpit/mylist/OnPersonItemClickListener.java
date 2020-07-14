package com.threedpit.mylist;

import android.view.View;

public interface OnPersonItemClickListener {
    // 어떤 아이템이 호출이 되면 이 함수를 호출 할것이다.
    public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position );
}
