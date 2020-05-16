package com.project.bluejackkos;

import java.util.Date;

public class Transaction {
    private int kosthumbnail;
    private String bookingId;
    private String userId;
    private String kosName;
    private String kosFacility;
    private int kosPrice;
    private String kosAddress;
    private float kosLongitude;
    private float kosLatitude;
    private String bookingDate;

    public Transaction(int kosthumbnail, String bookingId, String userId, String kosName, String kosFacility, int kosPrice, String kosAddress, float kosLongitude, float kosLatitude, String bookingDate) {
        this.kosthumbnail = kosthumbnail;
        this.bookingId = bookingId;
        this.userId = userId;
        this.kosName = kosName;
        this.kosFacility = kosFacility;
        this.kosPrice = kosPrice;
        this.kosAddress = kosAddress;
        this.kosLongitude = kosLongitude;
        this.kosLatitude = kosLatitude;
        this.bookingDate = bookingDate;
    }

    public int getKosThumbnail() {
        return kosthumbnail;
    }

    public void setKosThumbnail(int kosthumbnail) {
        this.kosthumbnail = kosthumbnail;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKosName() {
        return kosName;
    }

    public void setKosName(String kosName) {
        this.kosName = kosName;
    }

    public String getKosFacility() {
        return kosFacility;
    }

    public void setKosFacility(String kosFacility) {
        this.kosFacility = kosFacility;
    }

    public int getKosPrice() {
        return kosPrice;
    }

    public void setKosPrice(int kosPrice) {
        this.kosPrice = kosPrice;
    }

    public String getKosAddress() {
        return kosAddress;
    }

    public void setKosAddress(String kosAddress) {
        this.kosAddress = kosAddress;
    }

    public float getKosLongitude() {
        return kosLongitude;
    }

    public void setKosLongitude(float kosLongitude) {
        this.kosLongitude = kosLongitude;
    }

    public float getKosLatitude() {
        return kosLatitude;
    }

    public void setKosLatitude(float kosLatitude) {
        this.kosLatitude = kosLatitude;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
