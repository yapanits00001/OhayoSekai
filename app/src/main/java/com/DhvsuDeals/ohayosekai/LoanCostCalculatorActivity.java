package com.DhvsuDeals.ohayosekai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.DhvsuDeals.ohayosekai.databinding.ActivityLoanCostCalculatorBinding;

public class LoanCostCalculatorActivity extends AppCompatActivity {
    ActivityLoanCostCalculatorBinding calculatorBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculatorBinding = ActivityLoanCostCalculatorBinding.inflate(getLayoutInflater());
        setContentView(calculatorBinding.getRoot());

        String LoanType = getIntent().getStringExtra("PassLoanType");
        Long LoanTerm = getIntent().getLongExtra("PassLoanTermLimit", 0);
        String btnApply = getIntent().getStringExtra("PassButton");


        calculatorBinding.CalLoanType.setText(LoanType);
        calculatorBinding.CAlLoanTerm.setText(String.valueOf(LoanTerm));
        calculatorBinding.btnApplyLoan.setText(btnApply);

    }
}