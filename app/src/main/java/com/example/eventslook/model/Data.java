package com.example.eventslook.model;

import android.annotation.SuppressLint;
import android.text.Html;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.lang.Integer.parseInt;
import static java.lang.System.currentTimeMillis;

public class Data {

    private String course_id, fullname, category, startdate, enddate, description, image, organizers;

    public Data(String course_id, String fullname, String category, String startdate, String enddate, String description, String image, String organizers){
        this.course_id = course_id;
        this.fullname = fullname;
        this.category = category;
        this.startdate = startdate;
        this.enddate = enddate;
        this.description = description;
        this.image = image;
        this.organizers = organizers;
    }


    public String getCourseId() {
        return course_id;
    }

    public void setCourseId(String course_id) {
        this.course_id = course_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStartdate() {
            return convertToNormalTime(startdate);
    }
    public String getStartStamp() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        if (parseInt(enddate) - getCurrentTime() < 0){
            return "Мероприятие окончено.";
        }
        else{
            return convertToNormalTime(("Конец: "+ enddate));
        }
    }
    public String getEndStamp() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDescription() {
        if(description.isEmpty())
        {
            return "Описание отсутствует.";
        }
        else {
            return Html.fromHtml(description).toString();
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrganizers() {
        return organizers;
    }

    public void setOrganizers(String organizers) {
        this.organizers = organizers;
    }

    @SuppressLint("SimpleDateFormat")
    public String convertToNormalTime(String timestamp){
        String date;
        try {
            date = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date (Long.parseLong(timestamp)*1000));
            return date;
        }catch (NumberFormatException e){
            Long epoch = null;
            try {
                epoch = (new SimpleDateFormat("dd/MM/yyyy").parse(timestamp).getTime() / 1000);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            date = epoch.toString();

            return date;
        }
    }

    public Long getCurrentTime(){
        return currentTimeMillis();
    }
}

