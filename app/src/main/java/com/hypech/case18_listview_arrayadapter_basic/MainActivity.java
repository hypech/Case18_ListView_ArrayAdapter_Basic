package com.hypech.case18_listview_arrayadapter_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listv);
        String[] items={"Megan Fox","Scarlett Johansson","Jessica Alba","Katge Upton","Keira Knightley","Jennifer Lawrence"};

        arrayList=new ArrayList<>(Arrays.asList(items));
        arrayAdapter=new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Show input box
                showInputBox(arrayList.get(position),position);
            }
        });
    }

    public void showInputBox(String oldItem, final int index){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.input_box);
        TextView txtMessage=(TextView)dialog.findViewById(R.id.txtmessage);
        txtMessage.setText("Update item");
        txtMessage.setTextColor(Color.parseColor("#ff2222"));

        final EditText editText=(EditText)dialog.findViewById(R.id.txtinput);
        editText.setText(oldItem);
        Button bt=(Button)dialog.findViewById(R.id.btdone);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.set(index,editText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
