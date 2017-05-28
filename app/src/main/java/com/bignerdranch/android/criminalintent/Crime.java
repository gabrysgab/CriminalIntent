package com.bignerdranch.android.criminalintent;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Mateusz on 2017-04-10.
 */

public class Crime {

    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE, MMM dd, yyyy");
    public static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private UUID id;
    private String title;
    private Date date;
    private boolean solved;
    private boolean requiresPolice;

    public Crime() {
        this.id = UUID.randomUUID();
        date = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
       return this.date;
    }

    public boolean isRequiresPolice() {
        return requiresPolice;
    }

    public void setRequiresPolice(boolean requiresPolice) {
        this.requiresPolice = requiresPolice;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

}
