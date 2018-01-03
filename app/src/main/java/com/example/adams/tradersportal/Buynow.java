package com.example.adams.tradersportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Buynow extends AppCompatActivity {
    TextView textview4, textView6;
    EditText edittext2;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buynow);

        button2 = (Button)findViewById(R.id.button2);
        textview4 = (TextView) findViewById(R.id.textView4);
        textView6 = (TextView) findViewById(R.id.textView6);
        edittext2 = (EditText) findViewById(R.id.editText2);

        Intent i = getIntent();

        String name = i.getStringExtra("name");
        String price = i.getStringExtra("price");

        textview4.setText(name);
        textView6.setText(price);

        if (name != null && price != null) {

            textview4.setText(name);
            textView6.setText(price);

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (edittext2.getText().length() == 0) {
                        Toast mytoast = Toast.makeText(getApplicationContext(), "Field left empty!!", Toast.LENGTH_SHORT);
                        mytoast.show();
                    } else

                    {

                        Intent buyIntent = new Intent(Buynow.this, PaymentActivity.class);
                        startActivity(buyIntent);

                    }
                }

            });

        }
    }
}
