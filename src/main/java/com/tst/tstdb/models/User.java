package com.tst.tstdb.models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

   String name;

    @Column(name="max_total_points")
    int maxTotalPoints;

    public User() {
    }

    public User(String name, int maxTotalPoints) {
        this.name = name;
        this.maxTotalPoints = maxTotalPoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxTotalPoints() {
        return maxTotalPoints;
    }

    public void setMaxTotalPoints(int maxTotalPoints) {
        this.maxTotalPoints = maxTotalPoints;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxTotalPoints=" + maxTotalPoints +
                '}';
    }
}
