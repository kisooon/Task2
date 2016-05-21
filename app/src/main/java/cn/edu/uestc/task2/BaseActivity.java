package cn.edu.uestc.task2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by WuQishan on 2016/5/19.
 */
public class BaseActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }
    public void showThost(String str) {
        Toast.makeText(BaseActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}
