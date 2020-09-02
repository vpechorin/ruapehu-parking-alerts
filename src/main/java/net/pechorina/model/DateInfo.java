package net.pechorina.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DateInfo {
    @JsonProperty("isInPast")
    private Boolean inPast;

    private String localDate;
    private Integer percentFull;
    private Boolean bookable;
    private Integer capacity;

    public Boolean getInPast() {
        return inPast;
    }

    public void setInPast(Boolean inPast) {
        this.inPast = inPast;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public Integer getPercentFull() {
        return percentFull;
    }

    public void setPercentFull(Integer percentFull) {
        this.percentFull = percentFull;
    }

    public Boolean getBookable() {
        return bookable;
    }

    public void setBookable(Boolean bookable) {
        this.bookable = bookable;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String asMessage() {
        return String.format("Parking %s: percentage[%s] capacity[%s]", localDate, percentFull, capacity);
    }

    @Override
    public String toString() {
        return "DateInfo{" +
                "inPast=" + inPast +
                ", localDate=" + localDate +
                ", percentFull=" + percentFull +
                ", bookable=" + bookable +
                ", capacity=" + capacity +
                '}';
    }
}
