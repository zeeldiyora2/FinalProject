package com.example.poly.finalproject;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class tfFragment extends Fragment {

    String question,answer;
    EditText questionFiled;
    RadioGroup radioGroup;

    public tfFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tf, container, false);
        questionFiled= (EditText)view.findViewById(R.id.enterQuestion);
        radioGroup = (RadioGroup)view.findViewById(R.id.RGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.trueAns){
                    answer = "true";
                }
                else if (i == R.id.falseAns){
                    answer = "false";
                }
            }
        });
        return view;
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        question = questionFiled.getText().toString();
        data.add(question);
        data.add(answer);
        return data;
    }

}
