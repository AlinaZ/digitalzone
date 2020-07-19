package com.dz.visitcounter.view;

public class SuccessView {
    public String result = "success";

    public Integer todayVisits;

    public Integer uniqueVisitersToday;

    public Integer getTodayVisits() {
        return todayVisits;
    }

    public void setTodayVisits(Integer todayVisits) {
        this.todayVisits = todayVisits;
    }

    public Integer getUniqueVisitersToday() {
        return uniqueVisitersToday;
    }

    public void setUniqueVisitersToday(Integer uniqueVisitersToday) {
        this.uniqueVisitersToday = uniqueVisitersToday;
    }
}
