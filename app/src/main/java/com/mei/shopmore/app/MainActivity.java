package com.mei.shopmore.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final String CURRENT_BALANCE = "CURRENT_BALANCE";
    private static final String GOAL = "GOAL";

    private double currentBalanceValue;
    private double goalValue;

    TextView currentBalance;
    TextView goal;

    ProgressBar progressBar;

    Button skipped_button;
    Button reduce_button;
    Button reset_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            currentBalanceValue = 0.00;
            goalValue = 70.00;
        } else {
            currentBalanceValue = savedInstanceState.getDouble(CURRENT_BALANCE);
            goalValue = savedInstanceState.getDouble(GOAL);
        }

        currentBalance = (TextView) findViewById(R.id.current_balance);
        goal = (TextView) findViewById(R.id.goal);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        skipped_button = (Button) findViewById(R.id.skipped_button);
        reduce_button = (Button) findViewById(R.id.reduce_button);
        reset_button = (Button) findViewById(R.id.reset_button);

        setButtonOnClickListeners();

    }


    private void setButtonOnClickListeners() {
        skipped_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentBalanceValue = (currentBalanceValue + 10);
                updateCurrentBalance();
            }
        });

        reduce_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentBalanceValue = (currentBalanceValue + 5);
                updateCurrentBalance();
            }
        });

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentBalanceValue = 0;
                updateCurrentBalance();
            }
        });
    }

    private void updateCurrentBalance() {
        double progressValue = currentBalanceValue / goalValue * 100;
        currentBalance.setText("$" + String.format("%.02f", currentBalanceValue));
        progressBar.setProgress(((int) progressValue));
    }

    protected  void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(CURRENT_BALANCE, currentBalanceValue);
        outState.putDouble(GOAL, goalValue);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
