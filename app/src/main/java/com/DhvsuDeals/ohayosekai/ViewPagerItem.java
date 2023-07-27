package com.DhvsuDeals.ohayosekai;

public class ViewPagerItem {


    double Balance;
    String ShowBal = String.valueOf(Balance);
    String ItemName;

    public ViewPagerItem(String tittle, double balance) {

        this.Balance = balance;
        this.ItemName = tittle;
    }
}
