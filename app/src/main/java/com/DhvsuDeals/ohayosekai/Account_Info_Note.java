package com.DhvsuDeals.ohayosekai;

public class Account_Info_Note {

    public Account_Info_Note(){
        //public no-arg constructor needed
    }

    String Mem_ID;
    String Mem_Name;
    String Mem_Email;
    String Mem_Password;
    String Mem_Loan_Type;
    String Mem_Phone_Number;
    Double Mem_Loan_Outstanding_Balance, Mem_Savings_Balance;
    public Account_Info_Note(String mem_ID, String mem_Name, String mem_Email, String mem_Password, String mem_Loan_Type, String mem_Phone_Number, Double mem_Loan_Outstanding_Balance, Double mem_Savings_Balance) {
        Mem_ID = mem_ID;
        Mem_Name = mem_Name;
        Mem_Email = mem_Email;
        Mem_Password = mem_Password;
        Mem_Loan_Type = mem_Loan_Type;
        Mem_Phone_Number = mem_Phone_Number;
        Mem_Loan_Outstanding_Balance = mem_Loan_Outstanding_Balance;
        Mem_Savings_Balance = mem_Savings_Balance;
    }

    public String getMem_ID() {
        return Mem_ID;
    }

    public String getMem_Name() {
        return Mem_Name;
    }

    public String getMem_Email() {
        return Mem_Email;
    }

    public String getMem_Password() {
        return Mem_Password;
    }

    public String getMem_Loan_Type() {
        return Mem_Loan_Type;
    }

    public String getMem_Phone_Number() {
        return Mem_Phone_Number;
    }

    public Double getMem_Loan_Outstanding_Balance() {
        return Mem_Loan_Outstanding_Balance;
    }

    public Double getMem_Savings_Balance() {
        return Mem_Savings_Balance;
    }
}
