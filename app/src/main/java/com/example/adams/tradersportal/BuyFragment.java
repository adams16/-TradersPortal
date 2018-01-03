package com.example.adams.tradersportal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuyFragment extends Fragment implements Serializable{


    private TextView product_name, product_price;

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_buy, container, false);

        activity = getActivity();

        final ListView listView = (ListView) rootView.findViewById(R.id.listView);

        product_name = (TextView) rootView.findViewById(R.id.product_name);
        product_price = (TextView) rootView.findViewById(R.id.product_price);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SellProduct_ sellProduct_ = (SellProduct_) adapterView.getItemAtPosition(i);

                String name = sellProduct_.getName();
                String price = sellProduct_.getPrice();

                Intent pushdata = new Intent(getActivity(), Buynow.class);
                pushdata.putExtra("name", product_name.getText().toString());
                pushdata.putExtra("price", product_price.getText().toString());
                startActivity(pushdata);


                Toast.makeText(getActivity(), "Item Viewed" +name +price,Toast.LENGTH_SHORT).show();
            }
        });

        setList(listView);

        return rootView;
    }

    private void setList(ListView listView) {
        DatabaseHelper databaseHelper = new DatabaseHelper(activity.getApplicationContext());
        ArrayList<SellProduct_> sellProductArrayList = databaseHelper.fetchSellProducts();
        if (sellProductArrayList.size() > 0) {
            ProductListAdapter productListAdapter = new ProductListAdapter(activity, sellProductArrayList);
            listView.setAdapter(productListAdapter);
        } else {
            Toast.makeText(getActivity(), "Records not found", Toast.LENGTH_SHORT).show();
        }
    }
}
