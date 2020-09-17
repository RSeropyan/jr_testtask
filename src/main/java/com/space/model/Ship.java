package com.space.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

@Entity
@Table(name="ship")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        if (shipType != null) {
            return shipType.name();
        }
        else {
            return null;
        }
    }

    public void setShipType(String shipType) {
        this.shipType = ShipType.valueOf(shipType.toUpperCase());
    }

    public Long getProdDate() {
        if (prodDate != null) {
            return prodDate.getTime();
        }
        else {
            return null;
        }
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
        BigDecimal bd = new BigDecimal(Double.toString(speed));
        this.speed = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
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

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
