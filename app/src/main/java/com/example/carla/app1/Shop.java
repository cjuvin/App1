package com.example.carla.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop extends AppCompatActivity {

    private ImageButton CallButton;
    private Button AddButton;
    private List<HashMap<String, String>> _list;
    private ListView _view;
    private String LIST="list2.txt";
    private FileOutputStream out=null;
    private FileInputStream in=null;
    private ImageButton DeleteButton;
    private final static int ID_DIALOG_DELETE=0;
    private int _position;
    private boolean all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        _view = (ListView) findViewById(R.id.listView);
        this.AddButton = (Button) findViewById(R.id.AddButton);

        this.DeleteButton=(ImageButton) findViewById(R.id.DeleteButton);
        CallButton=(ImageButton)findViewById(R.id.CallButton);


        CallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAct = new Intent(getApplicationContext(),Call.class);
                startActivity(otherAct);
            }
        });



       AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAct = new Intent(getApplicationContext(),AddShop.class);
                startActivity(otherAct);
            }

        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        ItemsDAO itemsDAO = new ItemsDAO(this);
        itemsDAO.open();
        _list = itemsDAO.selectedList();

        final ListAdapter adapter = new SimpleAdapter(this,
                _list,
                android.R.layout.simple_list_item_2,
                new String[]{"Text1", "Text2"},
                new int[]{android.R.id.text1, android.R.id.text2});


        _view.setAdapter(adapter);
        _view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                all = false;
                _position = position;
                showDialog(ID_DIALOG_DELETE);
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all=true;
                showDialog(ID_DIALOG_DELETE);
                _view.setAdapter(adapter);
            }
        });

    }
}
