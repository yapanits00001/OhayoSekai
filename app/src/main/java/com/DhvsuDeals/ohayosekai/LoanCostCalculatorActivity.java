package com.DhvsuDeals.ohayosekai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.ActivityLoanCostCalculatorBinding;

public class LoanCostCalculatorActivity extends AppCompatActivity {
    ActivityLoanCostCalculatorBinding calculatorBinding;

    String[] items = {"3", "6", "9", "12"};
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculatorBinding = ActivityLoanCostCalculatorBinding.inflate(getLayoutInflater());
        setContentView(calculatorBinding.getRoot());

        adapterItems = new ArrayAdapter<String>(this, R.layout.drop_down_item_design, items);

        calculatorBinding.autoCompleteText.setAdapter(adapterItems);

        calculatorBinding.autoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                ChosenOne(item);
            }
        });


    }
    @SuppressLint("DefaultLocale")
    public void ChosenOne(String items){
        Double interest, BorrowMoney, loanOrigination, serviceFee, mutualFund, insurance, amountPayable, monthlyDue, amountReceivable;
        BorrowMoney = Double.parseDouble(calculatorBinding.txtBorrowMoney.getText().toString());

        switch (items){
           case "3":
               interest = .03 * BorrowMoney;
               serviceFee = .015 * BorrowMoney;
               mutualFund = .012 * BorrowMoney;
               insurance = .01 * BorrowMoney;
               loanOrigination = serviceFee + mutualFund + insurance;
               amountPayable = BorrowMoney + interest;
               monthlyDue = BorrowMoney/3;
               amountReceivable = BorrowMoney - loanOrigination;

               calculatorBinding.ViewTotalInterest.setText(String.valueOf(interest));
               calculatorBinding.ViewtotalOrigination.setText(String.valueOf(loanOrigination));
               calculatorBinding.ViewtotalServiceFee.setText(String.valueOf(serviceFee));
               calculatorBinding.ViewtotalMutualFund.setText(String.valueOf(mutualFund));
               calculatorBinding.ViewtotalInsurance.setText(String.valueOf(insurance));
               calculatorBinding.ViewTotalAmountPayable.setText(String.valueOf(amountPayable));
               calculatorBinding.ViewTotalMonthlyDue.setText(String.format("%.2f", monthlyDue));
               calculatorBinding.ViewTotalAmountReceivable.setText(String.valueOf(amountReceivable));
            case "6":
                interest = .06 * BorrowMoney;
                serviceFee = .015 * BorrowMoney;
                mutualFund = .012 * BorrowMoney;
                insurance = .01 * BorrowMoney;
                loanOrigination = serviceFee + mutualFund + insurance;
                amountPayable = BorrowMoney + interest;
                monthlyDue = BorrowMoney/6;
                amountReceivable = BorrowMoney - loanOrigination;

                calculatorBinding.ViewTotalInterest.setText(String.valueOf(interest));
                calculatorBinding.ViewtotalOrigination.setText(String.valueOf(loanOrigination));
                calculatorBinding.ViewtotalServiceFee.setText(String.valueOf(serviceFee));
                calculatorBinding.ViewtotalMutualFund.setText(String.valueOf(mutualFund));
                calculatorBinding.ViewtotalInsurance.setText(String.valueOf(insurance));
                calculatorBinding.ViewTotalAmountPayable.setText(String.valueOf(amountPayable));
                calculatorBinding.ViewTotalMonthlyDue.setText(String.format("%.2f", monthlyDue));
                calculatorBinding.ViewTotalAmountReceivable.setText(String.valueOf(amountReceivable));
                break;
            case "9":
                interest = .09 * BorrowMoney;
                serviceFee = .015 * BorrowMoney;
                mutualFund = .012 * BorrowMoney;
                insurance = .01 * BorrowMoney;
                loanOrigination = serviceFee + mutualFund + insurance;
                amountPayable = BorrowMoney + interest;
                monthlyDue = BorrowMoney/9;
                amountReceivable = BorrowMoney - loanOrigination;

                calculatorBinding.ViewTotalInterest.setText(String.valueOf(interest));
                calculatorBinding.ViewtotalOrigination.setText(String.valueOf(loanOrigination));
                calculatorBinding.ViewtotalServiceFee.setText(String.valueOf(serviceFee));
                calculatorBinding.ViewtotalMutualFund.setText(String.valueOf(mutualFund));
                calculatorBinding.ViewtotalInsurance.setText(String.valueOf(insurance));
                calculatorBinding.ViewTotalAmountPayable.setText(String.valueOf(amountPayable));
                calculatorBinding.ViewTotalMonthlyDue.setText(String.format("%.2f", monthlyDue));
                calculatorBinding.ViewTotalAmountReceivable.setText(String.valueOf(amountReceivable));
                break;
            case "12":
                interest = .12 * BorrowMoney;
                serviceFee = .015 * BorrowMoney;
                mutualFund = .012 * BorrowMoney;
                insurance = .01 * BorrowMoney;
                loanOrigination = serviceFee + mutualFund + insurance;
                amountPayable = BorrowMoney + interest;
                monthlyDue = BorrowMoney/12;
                amountReceivable = BorrowMoney - loanOrigination;
                calculatorBinding.ViewTotalInterest.setText(String.valueOf(interest));
                calculatorBinding.ViewtotalOrigination.setText(String.valueOf(loanOrigination));
                calculatorBinding.ViewtotalServiceFee.setText(String.valueOf(serviceFee));
                calculatorBinding.ViewtotalMutualFund.setText(String.valueOf(mutualFund));
                calculatorBinding.ViewtotalInsurance.setText(String.valueOf(insurance));
                calculatorBinding.ViewTotalAmountPayable.setText(String.valueOf(amountPayable));
                calculatorBinding.ViewTotalMonthlyDue.setText(String.format("%.2f", monthlyDue));
                calculatorBinding.ViewTotalAmountReceivable.setText(String.valueOf(amountReceivable));
                break;
       }
    }

}