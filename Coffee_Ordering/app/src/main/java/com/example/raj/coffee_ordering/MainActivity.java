package com.example.raj.coffee_ordering;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    String priceMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Billing");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


    }


    /*this method is for increment with plus sign*/
    public void increment(View view){
        if (quantity<100) {
            quantity = quantity + 1;
            display(quantity);
        }else
        {  /*hosting toast message*/
            Toast.makeText(this, "you can't have more than 100 coffee", Toast.LENGTH_SHORT).show();}

    }
    /*this method is for increment with minus sign*/
    public void decrement(View view) {
        if (quantity>1) {
            quantity = quantity - 1;
            display(quantity);
        }else
        {   /*hosting toast message*/
            Toast.makeText(this,"you can't have less than one coffee",Toast.LENGTH_SHORT).show();}
    }



    public void submitOrder(View view) {

        EditText fieldname = (EditText)findViewById(R.id.edit_text_view);
        String name = fieldname.getText().toString();
        int price;
        int baseprice=5;


        CheckBox hascheckbox = (CheckBox) findViewById(R.id.check_box);
        boolean addcheckbox = hascheckbox.isChecked() ;

        CheckBox hascheckbox1 = (CheckBox) findViewById(R.id.check1_box);
        boolean addcheckbox1 = hascheckbox1.isChecked() ;
        if(addcheckbox){
            baseprice = baseprice +1;
        }if (addcheckbox1){
            baseprice = baseprice + 2;
        }
        price = baseprice*quantity;
         priceMessage = createOrderSummary(name,price,addcheckbox,addcheckbox1);


           displayMessage(priceMessage);

    }


    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
/* string message */
    private String createOrderSummary(String name,int price,boolean addcheckbox,boolean addcheckbox1){
        String priceMessaage = "Name:" + name;
        priceMessaage += "\nAdd whipped Cream  " + addcheckbox;
        priceMessaage +="\nAdd chocolate cream " + addcheckbox1;
        priceMessaage += "\nQuantity " + quantity;
        priceMessaage += "\nTotal: $ " + price;
        priceMessaage += "Thank You!";
        return priceMessaage;
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.textview);
        priceTextView.setText(message);
    }


}
