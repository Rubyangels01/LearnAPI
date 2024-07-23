package com.example.learnapi.activity.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class CustomExpandableListView extends ExpandableListView {

    public CustomExpandableListView(Context context) {
        super(context);
    }

    public CustomExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpec);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}

