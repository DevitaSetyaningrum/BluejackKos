package com.project.bluejackkos;

public class Kos {
    private int id;
    private String kosName;
    private String kosFacility;
    private int kosPrice;
    private String kosThumbnail;
    private String kosAddress;
    private float kosLongitude;
    private float kosLatitude;

    public Kos(int id, String kosName, String kosFacility, int kosPrice, String kosThumbnail, String kosAddress, float kosLongitude, float kosLatitude) {
        this.id = id;
        this.kosName = kosName;
        this.kosFacility = kosFacility;
        this.kosPrice = kosPrice;
        this.kosThumbnail = kosThumbnail;
        this.kosAddress = kosAddress;
        this.kosLongitude = kosLongitude;
        this.kosLatitude = kosLatitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getKosThumbnail() {
        return kosThumbnail;
    }

    public void setKosThumbnail(String kosThumbnail) {
        this.kosThumbnail = kosThumbnail;
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

}
