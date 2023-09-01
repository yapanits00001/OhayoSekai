package com.DhvsuDeals.ohayosekai;

import com.google.firebase.Timestamp;

public class Notifications {

    String Sender, Mess_Tittle, Mess_Content;
    Timestamp Date_Received;

    public Notifications(String sender, String mess_Tittle, String mess_Content, Timestamp date_Received) {
        Sender = sender;
        Mess_Tittle = mess_Tittle;
        Mess_Content = mess_Content;
        Date_Received = date_Received;
    }
    public Notifications(){}

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getMess_Tittle() {
        return Mess_Tittle;
    }

    public void setMess_Tittle(String mess_Tittle) {
        Mess_Tittle = mess_Tittle;
    }

    public String getMess_Content() {
        return Mess_Content;
    }

    public void setMess_Content(String mess_Content) {
        Mess_Content = mess_Content;
    }

    public Timestamp getDate_Received() {
        return Date_Received;
    }

    public void setDate_Received(Timestamp date_Received) {
        Date_Received = date_Received;
    }




}
