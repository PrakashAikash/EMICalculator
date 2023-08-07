package com.aikash.emicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAnnualInterestRate;
    private EditText editTextNumberOfYears;
    private EditText editTextLoanAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAnnualInterestRate = findViewById(R.id.editTextAnnualInterestRate);
        editTextNumberOfYears = findViewById(R.id.editTextNumberOfYears);
        editTextLoanAmount = findViewById(R.id.editTextLoanAmount);

        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplayResults();
            }
        });
    }

    private void calculateAndDisplayResults() {
        String annualInterestRateString = editTextAnnualInterestRate.getText().toString();
        String numberOfYearString = editTextNumberOfYears.getText().toString();
        String loanString = editTextLoanAmount.getText().toString();

        if (annualInterestRateString.isEmpty() || numberOfYearString.isEmpty() || loanString.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        double annualInterestRate = Double.parseDouble(annualInterestRateString);
        double monthlyInterestRate = annualInterestRate / 1200;
        int numberOfYears = Integer.parseInt(numberOfYearString);
        double loanAmount = Double.parseDouble(loanString);

        double monthlyPayment = loanAmount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
        double totalPayment = monthlyPayment * numberOfYears * 12;
        monthlyPayment = Math.round(monthlyPayment * 100) / 100.0;
        totalPayment = Math.round(totalPayment * 100) / 100.0;

        String output = "The monthly payment is $ " + monthlyPayment +
                "\nThe total payment is $ " + totalPayment;

        Toast.makeText(this, output, Toast.LENGTH_LONG).show();
    }
}