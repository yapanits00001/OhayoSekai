package com.DhvsuDeals.ohayosekai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

        calculatorBinding.btnApplyLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_App_Form = new Intent(getApplicationContext(), LoanApplicationFormActivity.class);
                Go_App_Form.putExtra("PassLoanType", LoanType);
                startActivity(Go_App_Form);
                finish();
            }
        });
    }
}