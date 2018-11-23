package com.example.gerffyxuuu.assignment1;

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

public class SettingFragment extends Fragment {
    private List<SettingFragment.Person> persons;


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
        persons.add(new SettingFragment.Person("孙大伟", "肌肉撕裂者", R.drawable.trainer));
        persons.add(new SettingFragment.Person("鄂尔多斯", "肌肉破坏者", R.drawable.trainer));
        persons.add(new SettingFragment.Person("杨明", "没有肌肉者", R.drawable.trainer));
        persons.add(new SettingFragment.Person("孙大伟", "肌肉撕裂者", R.drawable.trainer));
        persons.add(new SettingFragment.Person("鄂尔多斯", "肌肉破坏者", R.drawable.trainer));
        persons.add(new SettingFragment.Person("杨明", "没有肌肉者", R.drawable.trainer));
    }



}
