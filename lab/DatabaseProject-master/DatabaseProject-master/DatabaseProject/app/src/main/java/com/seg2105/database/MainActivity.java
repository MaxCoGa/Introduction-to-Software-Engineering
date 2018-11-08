package com.seg2105.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox;
    EditText skuBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        skuBox = (EditText) findViewById(R.id.productSku);
    }



    public void newProduct (View view) {

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);//added

        int sku = Integer.parseInt(skuBox.getText().toString());

        Product product = new Product(productBox.getText().toString(), sku);

        // TODO: add to database
        dbHandler.addProduct(product);//add to database*****

        productBox.setText("");

        skuBox.setText("");


    }


    public void lookupProduct (View view) {

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);//added
        // TODO: get from Database
        Product product = dbHandler.findProduct(productBox.getText().toString());//null;

        if (product != null) {
            idView.setText(String.valueOf(product.getID()));
            skuBox.setText(String.valueOf(product.getSku()));
        } else {
            idView.setText("No Match Found");
        }




    }


    public void removeProduct (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);//added
        // TODO: remove from database
        boolean result = dbHandler.deleteProduct(productBox.getText().toString());//false;

        if (result) {
            idView.setText("Record Deleted");
            productBox.setText("");
            skuBox.setText("");
        } else
            idView.setText("No Match Found");

    }



    public void about(View view) {
        Intent aboutIntent = new Intent(this, About.class);
        startActivity(aboutIntent);
    }



}
