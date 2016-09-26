package qianfeng.asynclisttest_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> list;
    private boolean[] ischeck;

    private int[] imag = new int[]{
            R.drawable.a0, R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5,
            R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11,
            R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17,
            R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21, R.drawable.a22, R.drawable.a23,
            R.drawable.a24, R.drawable.a25, R.drawable.a26, R.drawable.a27, R.drawable.a28, R.drawable.a29,
            R.drawable.a30, R.drawable.a31, R.drawable.a32, R.drawable.a33, R.drawable.a34, R.drawable.a35,
            R.drawable.a36, R.drawable.a37, R.drawable.a38, R.drawable.a39, R.drawable.a40, R.drawable.a41,
            R.drawable.a42
    };
    private ListView lv;
    private MyBaseAdapter myBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        initInfo();
        this.myBaseAdapter = new MyBaseAdapter(this, list,ischeck);
        MyBaseAdapter myBaseAdapter = this.myBaseAdapter;
        lv.setAdapter(myBaseAdapter);


    }

    // 用ListView 做一个  多选的  checkbox
    // 要求有早上那3个按钮
    private void initInfo()
    {
        User user = null;
        list = new ArrayList<>();
        for(int i = 0; i < imag.length; i++)
        {
           user = new User(imag[i],"list:"+i);
            list.add(user);
        }
        ischeck = new boolean[list.size()];

    }

    public void onclick1(View view) {  // 全选按钮
        for (int i = 0; i < list.size(); i++) {
                ischeck[i] = true;
        }

    //    lv.setAdapter(myBaseAdapter);
        myBaseAdapter.notifyDataSetChanged(); // 更新数据一定要用这个

    }

    public void onclick2(View view) {  // 反选按钮

        for (int i = 0; i < list.size(); i++) {
            if(ischeck[i])
            {
                ischeck[i] = false;
            }else
            {
                ischeck[i] = true;
            }
    }
      //  lv.setAdapter(myBaseAdapter); // 更新lv里面的适配器
        myBaseAdapter.notifyDataSetChanged(); // 通知适配器，更新lv里面item的数据


    }

    public void onclick3(View view) {  // 查看所有已经选择的


    }
}
