package com.example.skipmaple.fragmentdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView topBar;
    private TextView tabControl;
    private TextView tabHome;
    private TextView tabPersonal;

    private FrameLayout ly_content;

    private FirstFragment f1, f2, f3;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        bindView();
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        topBar = (TextView)this.findViewById(R.id.txt_top);
        tabControl = (TextView)this.findViewById(R.id.txt_control);
        tabHome = (TextView)this.findViewById(R.id.txt_home);
        tabPersonal = (TextView)this.findViewById(R.id.txt_personal);

        ly_content = (FrameLayout) this.findViewById(R.id.fragment_container);

        tabControl.setOnClickListener(this);
        tabHome.setOnClickListener(this);
        tabPersonal.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    public void selected() {
        tabControl.setSelected(false);
        tabHome.setSelected(false);
        tabPersonal.setSelected(false);
    }

    //隐藏所有的Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            transaction.hide(f1);
        }
        if (f2 != null) {
            transaction.hide(f2);
        }
        if (f3 != null) {
            transaction.hide(f3);
        }
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (view.getId()) {
            case R.id.txt_control:
                selected();
                tabControl.setSelected(true);
                if (f1 == null) {
                    f1 = new FirstFragment("控制台界面");
                    transaction.add(R.id.fragment_container, f1);
                } else {
                    transaction.show(f1);
                }
                break;

            case R.id.txt_home:
                selected();
                tabHome.setSelected(true);
                if (f2 == null) {
                    f2 = new FirstFragment("主界面");
                    transaction.add(R.id.fragment_container, f2);
                } else {
                    transaction.show(f2);
                }
                break;

            case R.id.txt_personal:
                selected();
                tabPersonal.setSelected(true);
                if (f3 == null) {
                    f3 = new FirstFragment("个人中心界面");
                    transaction.add(R.id.fragment_container, f3);
                } else {
                    transaction.show(f3);
                }
                break;
        }

        transaction.commit();
    }
}
