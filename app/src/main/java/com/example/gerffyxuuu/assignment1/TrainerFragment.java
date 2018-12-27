package com.example.gerffyxuuu.assignment1;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TrainerFragment extends Fragment {
    private List<TrainerFragment.Person> persons;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.setting, container,false);
        RecyclerView rv = vi.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(llm);
        initializeData();
        ReCycleAdapter adapter = new ReCycleAdapter(persons);
        rv.setAdapter(adapter);
        return vi;
    }



    class Person {
        String name;
        String age;
        int photoId;

        Person(String name, String age, int photoId) {
            this.name = name;
            this.age = age;
            this.photoId = photoId;
        }
    }


    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    public void initializeData() {
        persons = new ArrayList<>();

        Intent intent = getActivity().getIntent();
        String nameString = intent.getStringExtra("name");

        Uri uri = Uri.parse("content://cn.scu.sql/user");
        ContentResolver contentResolver = getActivity().getContentResolver();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "张三");
        contentValues.put("age", "肌肉破坏者");
        contentValues.put("direction", "卧推，游泳");
        contentValues.put("intro", "拥有9年健身中心私教经验。在帮助客户通过定制锻炼计划和饮食计划实现健身目标方面拥有良好的记录。");
        contentValues.put("advice", "健身是对自己的身体负责的表现，而对自己的身体负责是一个人成熟的表现。一天中训练安排在什么时候并不重要，关键是每次训练时间要有一致性。当然，每个人的工作时间是有规律的");
        contentValues.put("pic", "R.drawable.trainer");


        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("name", "李四");
        contentValues2.put("age", "肌肉破坏者");
        contentValues2.put("direction", "卧推，游泳");
        contentValues2.put("intro", "拥有9年健身中心私教经验。在帮助客户通过定制锻炼计划和饮食计划实现健身目标方面拥有良好的记录。");
        contentValues2.put("advice", "健身是对自己的身体负责的表现，而对自己的身体负责是一个人成熟的表现。一天中训练安排在什么时候并不重要，关键是每次训练时间要有一致性。当然，每个人的工作时间是有规律的");
        contentValues2.put("pic", "R.drawable.trainer");

        contentResolver.insert(uri, contentValues);
        contentResolver.insert(uri, contentValues2);

        Cursor cursor = contentResolver.query(uri, null,null,null, null);;
        while (cursor.moveToNext()) {
            persons.add(new TrainerFragment.Person(cursor.getString(0), cursor.getString(4), R.drawable.trainer));
        }
        cursor.close();

    }



}
