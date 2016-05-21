package cn.edu.uestc.task2;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends BaseActivity {
//    private long passTime = 12 * 60 * 60 * 1000;
//    private int refreshTime = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
//        initEvents();

        new TitleBuilder(this)
                .setTitleText("算力")
                .setLeftImage(R.drawable.ico_btc)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showThost("标题栏左边的图片");
                    }
                })
                .setRightImage(R.drawable.ico_share)
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showThost("标题栏右边的图片");
                    }
                });


    }


    private void initViews() {
        RelativeLayout dv = (RelativeLayout) findViewById(R.id.dv);
        final DrawView view = new DrawView(this);
        view.setMinimumHeight(500);
        view.setMinimumWidth(300);
        //通知view组件重绘
        view.invalidate();
        dv.addView(view);
    }

//    protected void initEvents() {
//
//        dv.setWatcher(new DrawView.StateChangeWatcher() {
//            @Override
//            public void onStartClick() {
//                requestStartDig();
//            }
//        }
//    }
}