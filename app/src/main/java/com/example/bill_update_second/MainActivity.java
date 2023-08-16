package com.example.bill_update_second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    LinearLayout LinearLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        LinearLayout1 = findViewById(R.id.LinearLayout1);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.items_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set a listener to respond to item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            float price = 0.00F;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {

                    EditText quantityEditText = findViewById(R.id.editTextNumber);

                    quantityEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                            if (i == EditorInfo.IME_ACTION_DONE) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(quantityEditText.getWindowToken(), 0);

                                String quantityText = quantityEditText.getText().toString();
                                if (!quantityText.isEmpty()) {
                                    int quantity = Integer.parseInt(quantityText);
                                    addItem(position, quantity);
                                    display_bill_item();
                                }
                                return true;
                            }
                            return false;
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


    }


    private void addItem(int itemPosition, int quantity) {

        if (quantity >= 0){
            GlobalVars.numbers[itemPosition - 1] = quantity;
        }
    }


    public void display_bill_item(){

        LinearLayout1.removeAllViews();

        for (int i = 0; i<GlobalVars.numbers.length; i++){
            if (GlobalVars.numbers[i] > 0){

                View itemLayout = getLayoutInflater().inflate(R.layout.bill_item_layout, LinearLayout1, false);

                TextView textView = itemLayout.findViewById(R.id.itemTextView);
                textView.setText("  " + GlobalVars.items[i]);

                TextView quantityText = itemLayout.findViewById(R.id.itemEditText);
                quantityText.setText(String.valueOf(GlobalVars.numbers[i]));

                TextView textView2 = itemLayout.findViewById(R.id.priceTextView);
                float items_num = GlobalVars.numbers[i]* GlobalVars.prices[i];
                textView2.setText("        Rs." + String.valueOf(items_num));


                LinearLayout1.addView(itemLayout);

            }
        }

        float tot = GlobalVars.cal_tot();
        TextView total = findViewById(R.id.textView6);
        total.setText("Total - Rs."+ String.valueOf(tot));
    }




}