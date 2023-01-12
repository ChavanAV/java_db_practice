package com.example.dbmspractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button insrt,updt,dlt,vw;
    EditText name,rn,mark;
    DB_Helper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DB_Helper(MainActivity.this);

        name = findViewById(R.id.name);
        rn = findViewById(R.id.rn);
        mark = findViewById(R.id.mark);

        insrt = findViewById(R.id.insrt);
        updt = findViewById(R.id.updt);
        dlt = findViewById(R.id.dlt);
        vw = findViewById(R.id.vw);

        insrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getnametxt = name.getText().toString();
                String getrntxt = rn.getText().toString();
                String getmarktxt = mark.getText().toString();

                Boolean check = DB._insertData(getnametxt,getrntxt,getmarktxt);
                if (check== true){
                    Toast.makeText(MainActivity.this,"Successfull",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    rn.setText("");
                    mark.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this,"Not successfull",Toast.LENGTH_SHORT).show();
                }


            }
        });

        updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getname = name.getText().toString();
                String getrn = rn.getText().toString();
                String getmark = mark.getText().toString();
                Boolean check = DB.updateData(getname,getrn,getmark);
                if (check== true){
                    Toast.makeText(MainActivity.this,"Successfull",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    rn.setText("");
                    mark.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this,"Not successfull",Toast.LENGTH_SHORT).show();
                }
                name.setText("");
                rn.setText("");
                mark.setText("");

            }
        });

        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getname = name.getText().toString();
                Boolean check = DB.deleteData(getname);
                if (check== true){
                    Toast.makeText(MainActivity.this,"Successfull",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    rn.setText("");
                    mark.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this,"Not successfull",Toast.LENGTH_SHORT).show();
                }
            }
        });

        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor crsr = DB.viewData();
                if(crsr.getCount()==0){
                    Toast.makeText(MainActivity.this,"Not Data Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer sb = new StringBuffer();
                while (crsr.moveToNext()){
                    sb.append("Name :- "+crsr.getString(0)+"\n");
                    sb.append("Roll No :- "+crsr.getString(1)+"\n");
                    sb.append("Marks :- "+crsr.getString(2)+"\n\n");
                }
                AlertDialog.Builder alrt = new AlertDialog.Builder(MainActivity.this);
                alrt.setCancelable(true);
                alrt.setTitle("Student Info");
                alrt.setMessage(sb.toString());
                alrt.show();
            }
        });

    }
}