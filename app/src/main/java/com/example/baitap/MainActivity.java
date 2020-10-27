package com.example.baitap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spin1,spin2;
    private TextView from,to,end;
    private EditText value;
    private int[] idNumber={
            R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,
            R.id.btn7,R.id.btn8,R.id.btn9
    };
    private int[] idOther={
            R.id.point, R.id.btnDot,R.id.dash,R.id.done,R.id.del
    };
    String[] items={
            "USD","VND","KRW","Euro","JPY","CNY","RUB","GBP","AUD","HKD"
    };
    String str1, str2;
    Double d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
    }
    public void connectView(){
        from=(TextView) findViewById(R.id.from);
        spin1=(Spinner) findViewById(R.id.spin1);
        to=(TextView) findViewById(R.id.to);
        spin2=(Spinner) findViewById(R.id.spin2);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spin1.setAdapter(adapter);
        spin2.setAdapter(adapter);
        spin1.setOnItemSelectedListener(this);
        spin1.setOnItemSelectedListener(this);

        value=(EditText) findViewById(R.id.value);
        for (int i = 0; i < idNumber.length; i++){
            findViewById(idNumber[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    for (int i = 0; i < idNumber.length ; i++) {
                        if (id == idNumber[i]) {
                            String text = ((Button) findViewById(id)).getText().toString();
                            String s=value.getText().toString()+text;
                            value.setText(s);
                            return;
                        }
                    }
                }
            });
        }
        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Double.parseDouble(value.getText().toString())!=0)
                {
                    value.append(".");
                }else {
                    value.setText("0.");
                }

            }
        });
        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=value.getText().toString();
                if(s.length()<=1)
                    value.setText("");
                else{
                    s=s.substring(0,s.length()-1);
                    value.setText(s);
                }

            }
        });

        findViewById(R.id.dash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value.getText().toString()!="")
                    value.append(",");
            }
        });
        findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromTo();
            }
        });
    }
    public void fromTo(){
        d= Double.parseDouble(value.getText().toString());
        switch (str1){
            case "USD":
                d *=23176;
                break;
            case "VND":
                d=d;break;
            case "KRW":
                d*=20.55;
                break;
            case "Euro":
                d *= 27484;
                break;
            case "JPY":
                d *=221.3;
                break;
            case "CNY":
                d *=3464.64;
                break;
            case "RUB":
                d *= 304.6;
                break;
            case "GBP":
                d *= 30228;
                break;
            case "AUD":
                d*=16535.08;
                break;
            case "HKD":
                d *= 2990.37;
                break;
        }
        switch (str2){
            case "USD":
                d /=23176;
                break;
            case "VND":
                d=d;break;
            case "KRW":
                d/=20.55;
                break;
            case "Euro":
                d /= 27484;
                break;
            case "JPY":
                d /=221.3;
                break;
            case "CNY":
                d /=3464.64;
                break;
            case "RUB":
                d /= 304.6;
                break;
            case "GBP":
                d /= 30228;
                break;
            case "AUD":
                d/=16535.08;
                break;
            case "HKD":
                d /= 2990.37;
                break;
        }
        end=(TextView) findViewById(R.id.end) ;
        end.setText(d.toString());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int i =view.getId();
        if(i==R.id.spin1){
            str1=items[position];
        }
        else str2=items[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}