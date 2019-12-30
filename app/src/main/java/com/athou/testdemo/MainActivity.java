package com.athou.testdemo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

/**
 * @author yunshan
 * @date 2019-12-29
 */
public class MainActivity extends AppCompatActivity {

    public static int dip2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }

    private ConstraintSet resetConstraintSet = new ConstraintSet();

    private ConstraintSet applyConstraintSet1 = new ConstraintSet();
    private ConstraintSet applyConstraintSet2 = new ConstraintSet();
    private ConstraintSet applyConstraintSet3 = new ConstraintSet();
    private ConstraintSet applyConstraintSet4 = new ConstraintSet();

    private ConstraintLayout testParent;
    private TestLayout layout1;
    private TestLayout layout2;
    private TestLayout layout3;
    private TestLayout layout4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testParent = findViewById(R.id.testParent);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);

        //填充册数数据
        layout1.setData("1");
        layout2.setData("2");
        layout3.setData("3");
        layout4.setData("4");

        //保存原始状态
        resetConstraintSet.clone(testParent);
        //分别存储4种不同状态
        applyConstraintSet1.clone(this, R.layout.layout1);
        applyConstraintSet2.clone(this, R.layout.layout2);
        applyConstraintSet3.clone(this, R.layout.layout3);
        applyConstraintSet4.clone(this, R.layout.layout4);

        //设置长按事件
        layout1.setOnLongClickListener(longClickListener);
        layout2.setOnLongClickListener(longClickListener);
        layout3.setOnLongClickListener(longClickListener);
        layout4.setOnLongClickListener(longClickListener);
    }

    private View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            TestLayout target = (TestLayout) view;
            switch (target.getSize()) {
                case Large:
                    recoverToMidle();
                    break;
                case Mid:
                    changeToLarge(target);
                    break;
                case Small:
                    changeToLarge(target);
                    break;
            }
            return true;
        }
    };

    private void recoverToMidle() {
        //重置每个模块到mid模式
        layout1.setSize(Size.Mid);
        layout2.setSize(Size.Mid);
        layout3.setSize(Size.Mid);
        layout4.setSize(Size.Mid);

        resetConstraintSet.applyTo(testParent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AutoTransition transition = new AutoTransition();
            //可以设置动画时长
//            transition.duration = 500
            TransitionManager.beginDelayedTransition(testParent, transition);
        }
    }

    private void changeToLarge(TestLayout target) {
        //先重置每个模块到small模式
        layout1.setSize(Size.Small);
        layout2.setSize(Size.Small);
        layout3.setSize(Size.Small);
        layout4.setSize(Size.Small);
        //在将当前模块设为large
        target.setSize(Size.Large);

        ConstraintSet applyConstraintSet;
        if (target == layout1) {
            applyConstraintSet = applyConstraintSet1;
        } else if (target == layout2) {
            applyConstraintSet = applyConstraintSet2;
        } else if (target == layout3) {
            applyConstraintSet = applyConstraintSet3;
        } else if (target == layout4) {
            applyConstraintSet = applyConstraintSet4;
        } else {
            applyConstraintSet = resetConstraintSet;
        }
        applyConstraintSet.applyTo(testParent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AutoTransition transition = new AutoTransition();
            //可以设置动画时长
//            transition.duration = 500
            TransitionManager.beginDelayedTransition(testParent, transition);
        }
    }
}