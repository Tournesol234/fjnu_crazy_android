package com.example.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 假设这是你的布局文件，其中包含了ListView控件
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // 创建数据源
        String[] animals = {"Lion", "Tiger", "Monkey", "Dog", "Cat"};
        int[] images = {R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog, R.drawable.cat};

        // 将数据和图片放入Map集合中
        dataList = new ArrayList<>();
        for (int i = 0; i < animals.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", animals[i]);
            map.put("image", images[i]);
            dataList.add(map);
        }

        // 创建SimpleAdapter适配器
        adapter = new SimpleAdapter(this, dataList,
                R.layout.simpleadapter, // 每个列表项的布局文件
                new String[]{"name", "image"}, // 数据源的键名
                new int[]{R.id.nameTextView, R.id.imageView}); // 对应的视图ID

        // 设置适配器到ListView
        listView.setAdapter(adapter);

        // 为ListView设置点击事件监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取被点击的项的数据
                Map<String, Object> itemData = dataList.get(position);

                // 显示一个Toast消息框，包含选中的动物名称
                Toast.makeText(MainActivity.this, "You selected: " + itemData.get("name"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
