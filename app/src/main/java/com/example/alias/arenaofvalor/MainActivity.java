package com.example.alias.arenaofvalor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    public ArrayList<Hero> heroes= new ArrayList();
    MyAdapter adapter;
    ImageView pic;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hero h=new Hero("","廉颇","地狱炎魂","上单",100,30,40,40);

        heroes.add(h);

        Button add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                View newView = factory.inflate(R.layout.dialoglayout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                 pic =  newView.findViewById(R.id.picture);

                pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();

                        intent.setAction(Intent.ACTION_OPEN_DOCUMENT );

                        intent.setType("image/*");

                        startActivityForResult(intent, 0);
                    }
                });






                builder.setView(newView);
                builder.setTitle("添加英雄");
                builder.setPositiveButton("确认添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("放弃修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                    }
                });



                builder.show();

            }


        });


        adapter = new MyAdapter(MainActivity.this, heroes);
        ListView lv=findViewById(R.id.lw);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(MainActivity.this,Info.class);

                intent.putExtra("hero",heroes.get(position));
                startActivityForResult(intent,1);



            }

        });




    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null) {


            uri = data.getData();


           pic.setImageURI(uri);

        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}
