package com.example.taxcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.*;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends AppCompatActivity {
    myCalcClass calcObj = new myCalcClass();

    //constants used for saving
    private static final String BILL_TOTAL = "BILL TOTAL";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

    private double currentBillTotal;
    private double currentCustomPercent;

    //step one to get xml values
    private EditText tip10EditText;
    private EditText tip15EditText;
    private EditText tip20EditText;
    private EditText billEditText;
    private EditText total10EditText;
    private EditText total15EditText;
    private EditText total20EditText;
    private EditText tipCustomEditText;
    private EditText totalCustomEditText;
    private TextView customTipTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step two, connecting variables to xml values
        tip10EditText = (EditText) findViewById(R.id.tip10EditText);
        tip15EditText = (EditText) findViewById(R.id.tip15EditText);
        tip20EditText = (EditText) findViewById(R.id.tip20EditText);
        billEditText = (EditText) findViewById(R.id.billEditText);
        total10EditText = (EditText) findViewById(R.id.total10EditText);
        total15EditText = (EditText) findViewById(R.id.total15EditText);
        total20EditText = (EditText) findViewById(R.id.total20EditText);
        tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
        totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);
        customTipTextView = (TextView) findViewById(R.id.customTipTextView);

        SeekBar customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListender);

        billEditText.addTextChangedListener(billEditTextWatcher);



    }

    private void updateStandard() {
        double tip10Perc = currentBillTotal * .1;
        tip10EditText.setText(String.format("%.2f", tip10Perc));
        total10EditText.setText(String.format("%.2f", (currentBillTotal + tip10Perc)));
        double tip15Perc = currentBillTotal * .15;
        tip15EditText.setText(String.format("%.2f", tip15Perc));
        total15EditText.setText(String.format("%.2f", (currentBillTotal + tip15Perc)));
        double tip20Perc = currentBillTotal * .2;
        tip20EditText.setText(String.format("%.2f", tip20Perc));
        total20EditText.setText(String.format("%.2f", (currentBillTotal + tip20Perc)));
    }


    private void updateCustom()
    {
        customTipTextView.setText(currentCustomPercent + "%");
        double customTipAmount = currentBillTotal * currentCustomPercent * 0.01;
        double customTotalAmount = currentBillTotal + customTipAmount;
        tipCustomEditText.setText(String.format("%.2f", customTipAmount));
        totalCustomEditText.setText(String.format("%.2f", customTotalAmount));

    }







    private OnSeekBarChangeListener customSeekBarListender = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            currentCustomPercent = seekBar.getProgress();
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };


    private TextWatcher billEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                currentBillTotal = Double.parseDouble(s.toString());
            }catch (NumberFormatException e) {
                currentBillTotal = 0.0;
            }
            updateStandard();
            updateCustom();


        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}