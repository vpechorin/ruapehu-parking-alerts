package net.pechorina.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PRecord {
    private Boolean hasPrevious;

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private Date previous;

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private Date next;

    private List<DateInfo> dates;

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public Date getPrevious() {
        return previous;
    }

    public void setPrevious(Date previous) {
        this.previous = previous;
    }

    public Date getNext() {
        return next;
    }

    public void setNext(Date next) {
        this.next = next;
    }

    public List<DateInfo> getDates() {
        return dates;
    }

    public void setDates(List<DateInfo> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "Record{" +
                "hasPrevious=" + hasPrevious +
                ", previous=" + previous +
                ", next=" + next +
                ", dates=" + dates +
                '}';
    }
}
