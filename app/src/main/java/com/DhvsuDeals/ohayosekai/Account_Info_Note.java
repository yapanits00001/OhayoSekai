package com.DhvsuDeals.ohayosekai;

import com.google.firebase.Timestamp;

public class Account_Info_Note {

    public Account_Info_Note(){
        //public no-arg constructor needed
    }

    String Mem_ID, Mem_EMP_ID, Mem_Email,Mem_Password, Mem_Name, Mem_Sex,  Mem_Loan_Type, Mem_Phone_Number, Mem_Dept_Inst, Mem_Stats_Employment;
    Double Mem_Monthly_Salary;
    Timestamp Birth_Date;

    public Account_Info_Note(String mem_ID, String mem_EMP_ID, String mem_Email, String mem_Password, String mem_Name, String mem_Sex, String mem_Phone_Number, String mem_Dept_Inst, String mem_Stats_Employment, Double mem_Monthly_Salary, Timestamp birth_Date) {
        Mem_ID = mem_ID;
        Mem_EMP_ID = mem_EMP_ID;
        Mem_Email = mem_Email;
        Mem_Password = mem_Password;
        Mem_Name = mem_Name;
        Mem_Sex = mem_Sex;
        Mem_Phone_Number = mem_Phone_Number;
        Mem_Dept_Inst = mem_Dept_Inst;
        Mem_Stats_Employment = mem_Stats_Employment;
        Mem_Monthly_Salary = mem_Monthly_Salary;
        Birth_Date = birth_Date;
    }

    public String getMem_ID() {
        return Mem_ID;
    }

    public String getMem_EMP_ID() {
        return Mem_EMP_ID;
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

    public Double getMem_Monthly_Salary() {
        return Mem_Monthly_Salary;
    }

    public Timestamp getBirth_Date() {
        return Birth_Date;
    }
}
