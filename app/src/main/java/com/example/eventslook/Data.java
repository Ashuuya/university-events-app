package com.example.eventslook;
import java.util.Date;
import java.text.SimpleDateFormat;

import static java.lang.Integer.parseInt;

public class Data {

    private String course_id;

    private String fullname;

    private String category;

    private String startdate;

    private String enddate;

    private String description;

    private String image;

    private String organizers;


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

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return convertToNormalTime(enddate);
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDescription() {
        return description;
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

    public String convertToNormalTime(String date){

        date = Integer.toString(parseInt(date)*1000);
        Date currentDate = new Date (parseInt(date));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String normalDate = dateFormat.format(currentDate);

        return normalDate;
    }
}

