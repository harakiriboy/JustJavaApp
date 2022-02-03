package com.example.justjavaapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckbox = findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);

        boolean hasWhippedCream =  whippedCreamCheckbox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();

        Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);
        Log.v("MainActivity", "Has whipped cream:chocolate " + hasChocolate);

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if(quantity > 0){
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    /**
     This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     This method creates summary for order.
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = " Name: Dear " + name + "!";
        priceMessage += "\n Has Whipped Cream: " + addWhippedCream;
        priceMessage += "\n Has Chocolate: " + addChocolate;
        priceMessage += "\n Quantity: " + quantity;
        priceMessage += "\n Total: $" + price;
        priceMessage += "\n Thank you!";
        return priceMessage;
    }

    /**
     This method displays the given message on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     This method calculates the price.
     */
    private int calculatePrice(boolean whipped, boolean choco) {
        int base_price = 5;
        if(whipped == true) {
            base_price += 1;
        }
        if(choco == true) {
            base_price += 2;
        }
        return base_price * quantity;
    }
}
