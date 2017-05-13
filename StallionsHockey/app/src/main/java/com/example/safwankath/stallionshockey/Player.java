package com.example.safwankath.stallionshockey;

import android.app.Fragment;

/**
 * Created by Safwan Kath on 29/07/2016.
 */
public class Player{

    private String fname;
    private String lname;
    private int page;
    private int rating;
    private boolean pstatus;

    // constructor
    public Player(String first, String last, int age, int rate, boolean status){
        fname=first;
        lname=last;
        page=age;
        rating=rate;
        pstatus=status;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isPstatus() {
        return pstatus;
    }

    public void setPstatus(boolean pstatus) {
        this.pstatus = pstatus;
    }
}
