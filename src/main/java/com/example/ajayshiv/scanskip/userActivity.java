package com.example.ajayshiv.scanskip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class userActivity extends AppCompatActivity {

    private Button camPic;
    private QRHandler qr;
    private Database database;
    private String store ;
    private String list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        camPic = (Button) findViewById(R.id.cam);
        camPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCam = new Intent(userActivity.this, camera.class);
                startActivityForResult(iCam, 0);
            }
        });
        list ="";
        store = "ExampleStore";
        Product[] shopList = readCart(qr.barcodes);
        TextView ItemList= (TextView) findViewById(R.id.ItemList);
        for (Product p : shopList) {
            list = list + p.name + "\t\t\t" + p.price + "\n";
        }
        ItemList.setText(list);


    }
    public Product[] readCart(List<String> barcodes) {
        Product[] shoppingCart = new Product[barcodes.size()+1];
        Database a = new Database(store);
        double tot=0;
        int i=0;
        while(i<barcodes.size()) {
            Product prod = a.get(barcodes.get(i));
            shoppingCart[i] = prod;
            tot += prod.price;
            i++;
        }
        shoppingCart[i] = new Product("Total", tot);
        return shoppingCart;
    }
}
