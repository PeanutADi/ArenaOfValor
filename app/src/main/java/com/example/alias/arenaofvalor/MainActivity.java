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
import android.widget.EditText;
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

        final  AlertDialog dialog1 = new AlertDialog.Builder(this).create();//创建对话框

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                dialog1.setIcon(R.mipmap.ic_launcher);//设置对话框icon


                dialog1.setMessage("Delete or not");//设置文字显示内容

                dialog1.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                    }
                });

                dialog1.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        heroes.remove(position);
                        adapter.notifyDataSetChanged();

                        dialog.dismiss();//关闭对话框
                    }
                });


                dialog1.show();





                return true;
            }
        });



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



                final EditText name=newView.findViewById(R.id.name);
                final EditText alias=newView.findViewById(R.id.alias);
                final EditText position=newView.findViewById(R.id.position);
                final EditText live=newView.findViewById(R.id.live);
                final EditText attack=newView.findViewById(R.id.attack);
                final EditText skill=newView.findViewById(R.id.skill);
                final EditText difficulty=newView.findViewById(R.id.difficulty);







                builder.setView(newView);
                builder.setTitle("添加英雄");
                builder.setPositiveButton("确认添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String _uri;
                        if(uri==null) _uri="";
                        else _uri=uri.toString();

                        String _name=name.getText().toString();
                        String _alias=alias.getText().toString();
                        String _position=position.getText().toString();
                        int _live= Integer.parseInt(live.getText().toString());
                        int _attack= Integer.parseInt(attack.getText().toString());
                        int _skill= Integer.parseInt(skill.getText().toString());
                        int _difficulty= Integer.parseInt(difficulty.getText().toString());
                        Hero h=new Hero(_uri,_name,_alias,_position,_live,_attack,_skill,_difficulty);

                        heroes.add(h);

                        adapter.notifyDataSetChanged();


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
