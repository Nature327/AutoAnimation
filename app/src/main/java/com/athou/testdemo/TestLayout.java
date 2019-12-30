package com.athou.testdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author yunshan
 * @date 2019-12-29
 */
public class TestLayout extends FrameLayout {
    public TestLayout(Context context) {
        this(context, null);
    }

    public TestLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Size mSize = Size.Mid;

    private ImageView icon;
    private TextView testTv;
    private TextView bottomTv;

    private void init(Context context) {
        View.inflate(context, R.layout.item_layout, this);

        icon = findViewById(R.id.icon);
        testTv = findViewById(R.id.testTv);
        bottomTv = findViewById(R.id.bottomTv);

        bottomTv.setPivotX(0f);
        bottomTv.setPivotY(0.5f);
    }

    public Size getSize() {
        return mSize;
    }

    public void setSize(Size size) {
        if (mSize != size) {
            this.mSize = size;
            sizeChange();
        }
    }

    private void sizeChange() {
        icon.setScaleX(mSize.getRactor());
        icon.setScaleY(mSize.getRactor());

        testTv.setScaleX(mSize.getRactor());
        testTv.setScaleY(mSize.getRactor());

        bottomTv.setScaleX(mSize.getRactor());
        bottomTv.setScaleY(mSize.getRactor());
    }

    public void setData(String data) {
        testTv.setText(data);
    }
}
