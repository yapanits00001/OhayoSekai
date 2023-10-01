package com.DhvsuDeals.ohayosekai;

public class Account_Info_Note {

    public Account_Info_Note(){
        //public no-arg constructor needed
    }

    String Mem_ID, Mem_Email,Mem_Password, Mem_Name, Mem_Sex,  Mem_Loan_Type, Mem_Phone_Number, Mem_Dept_Inst, Mem_Stats_Employment;
    Double Mem_Loan_Outstanding_Balance, Mem_Savings_Balance;

    public Account_Info_Note(String mem_ID, String mem_Email, String mem_Password, String mem_Name, String mem_Sex, String mem_Loan_Type, String mem_Phone_Number, String mem_Dept_Inst, String mem_Stats_Employment, Double mem_Loan_Outstanding_Balance, Double mem_Savings_Balance) {
        Mem_ID = mem_ID;
        Mem_Email = mem_Email;
        Mem_Password = mem_Password;
        Mem_Name = mem_Name;
        Mem_Sex = mem_Sex;
        Mem_Loan_Type = mem_Loan_Type;
        Mem_Phone_Number = mem_Phone_Number;
        Mem_Dept_Inst = mem_Dept_Inst;
        Mem_Stats_Employment = mem_Stats_Employment;
        Mem_Loan_Outstanding_Balance = mem_Loan_Outstanding_Balance;
        Mem_Savings_Balance = mem_Savings_Balance;
    }

    public String getMem_ID() {
        return Mem_ID;
    }

    public String getMem_Email() {
        return Mem_Email;
    }

    public String getMem_Password() {
        return Mem_Password;
    }

    public String getMem_Name() {
        return Mem_Name;
    }

    public String getMem_Sex() {
        return Mem_Sex;
    }

    public String getMem_Loan_Type() {
        return Mem_Loan_Type;
    }

    public String getMem_Phone_Number() {
        return Mem_Phone_Number;
    }

    public String getMem_Dept_Inst() {
        return Mem_Dept_Inst;
    }

    public String getMem_Stats_Employment() {
        return Mem_Stats_Employment;
    }

    public Double getMem_Loan_Outstanding_Balance() {
        return Mem_Loan_Outstanding_Balance;
    }

    public Double getMem_Savings_Balance() {
        return Mem_Savings_Balance;
    }
}
