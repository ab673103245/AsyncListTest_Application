package qianfeng.asynclisttest_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<User> list;
    private LayoutInflater inflater;
    private boolean[] ischeck;

    public MyBaseAdapter(Context context, List<User> list,boolean[] ischeck) {
        this.context = context;
        this.list = list;
        this.ischeck = ischeck;
        inflater = LayoutInflater.from(context);
    }

    public MyBaseAdapter() {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 新的数据源要加载时，就会调用这里面的方法，还是用原来的视图，只不过是 数据发生了更改。
    // 只更新你更新了的数据，样式如果你没有改变，便会保留进下一个item中
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
// 换一种方式，用一个数组来记录。
        ViewHolder holder = null;

        if(convertView == null) // 加载第一屏数据
        {
            convertView = inflater.inflate(R.layout.checkbox,parent,false);
            // 记得new ViewHolder，让8个holder对象来记录 具体的控件的位置，而且每个holder对象所记录的位置都是不同的,一个holder对象记录一个item框的内容
            holder = new ViewHolder();  // 只要有new，堆中就有东西，holder只是栈用来临时记录的地址而已，真正的对象是在堆中，而且已经通过setTag（），存储在标签中了
            holder.iv = ((ImageView) convertView.findViewById(R.id.iv));
            holder.tv = ((TextView) convertView.findViewById(R.id.tv));
            holder.cb = ((CheckBox) convertView.findViewById(R.id.cb));
            convertView.setTag(holder);        // 让初次加载图片的convertView,内部携带自己的控件的具体位置,
            // 这样，每个convertView就能唯一锁定自己的位置，而不用频繁开辟，只需开辟一次就够了，
            // 因为它记录了自己的位置 --->利用holder对象，八个都不同哦，holder是栈的，实际setTag中所指向的地址，确实指向堆中的，
            // 而且八个都不同，因为每个都是新new出来，然后用holder追踪它的地址的。

        }else
        {
            holder = ((ViewHolder) convertView.getTag()); // 把 Tag中记录的堆中的具体拿出来嘛，一共有九个嘛 0-7 , 8 一共九个
        }
/*
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ischeck[position] = isChecked;
            }
        });

       if(ischeck[position])
       {
           holder.cb.setChecked(true);
       }else
       {
           holder.cb.setChecked(false);
       }
       */

        if(ischeck[position])
        {
            holder.cb.setChecked(true);
        }else{
            holder.cb.setChecked(false);
        }

        holder.cb.setOnClickListener(new View.OnClickListener() {
            boolean is = true;
            @Override
            public void onClick(View v) {
               ischeck[position] = is;
                is = !is;

            }
        });
//        holder.cb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ischeck[position] =
//            }
//        });

        User user = list.get(position);
        holder.iv.setImageResource(user.getUserface());
        holder.tv.setText(user.getUsername());


        return convertView; // 把这个convertView返回给ListView，

    }

    class ViewHolder{
        ImageView iv;
        TextView tv;
        CheckBox cb;
    }
}
