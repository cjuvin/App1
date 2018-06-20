package com.example.carla.app1;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddShop extends AppCompatActivity {

    private ListView ItemsView;
    private EditText AddText;
    private List<HashMap<String, String>> _list;
    public final static String Nom= "com.example.carla.app1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        _list = new ArrayList<HashMap<String, String>>();
        ItemsView=(ListView)findViewById(R.id.listView);
        AddText =(EditText)findViewById(R.id.AddText) ;
        HashMap<String, String> element = new HashMap<>();

        final ItemsDAO itemsDAO= new ItemsDAO(this);
        itemsDAO.open();





        ItemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
              Items i = itemsDAO.selectByID(getItemId(position));
             itemsDAO.upDateItems(i);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        _list.clear();

    }



    public Long getItemId(int position)
    {
        HashMap<String, String> element =_list.get(position);
        String _id = element.get("id");
        return (Long.parseLong(_id));
    }

    @Override
    protected void onStart() {
        super.onStart();
        _list.clear();

        ItemsDAO itemsDAO= new ItemsDAO(this);
        itemsDAO.open();



       _list = itemsDAO.select20();


        final ListAdapter adapter = new SimpleAdapter(this,
                _list,
                android.R.layout.simple_list_item_multiple_choice,
                new String[]{"Text1"},
                new int[]{android.R.id.text1});


        ItemsView.setAdapter(adapter);
        for (int i = 0; i < ItemsView.getCount(); i++) {
            // For every other item in the list, set as checked.
            Items items=itemsDAO.selectByID( getItemId(i));
            if (items.getIn()==1)
            ItemsView.setItemChecked(i,true);
            }
        }



        /*AddText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
	_list = itemsDAO.selectByName(addStr);

                element.put("Text1", AddText.getText().toString());
                element.put("Text2", "1");
                element.put("id", "1");
                element.put("in", "0");
                _list.remove(0);
                _list.add(0, element);
                ItemsView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });*/
    }

