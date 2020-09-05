package com.space.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="ship", schema="cosmoport")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "planet")
    private String planet;

    @Column(name = "shipType")
    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Column(name = "prodDate")
    private Date prodDate;

    @Column(name = "isUsed")
    private Boolean isUsed;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "crewSize")
    private Integer crewSize;

    @Column(name = "rating")
    private Double rating;

    public Ship() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getShipType() {
        return shipType.name();
    }

    public void setShipType(String shipType) {
        this.shipType = ShipType.valueOf(shipType);
    }

    public Long getProdDate() {
        return prodDate.getTime();
    }

    public void setProdDate(Long prodDate) {
        this.prodDate = new Date(prodDate);
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = Math.floor(100 * speed) / 100;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void calculateRating() {
        double k = isUsed ? 0.5 : 1.0;
        double v = speed;
        int y0 = 3019;
        int y1 = prodDate.getYear() + 1900;
        double r = (80 * v * k) / (y0 - y1 + 1);
        this.rating = Math.floor(100 * r) / 100;
    }

}