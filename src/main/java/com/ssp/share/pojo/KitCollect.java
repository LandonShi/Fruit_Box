package com.ssp.share.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "kitcollect")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class KitCollect implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="kid")
    private Kit kit;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "KitCollect{" +
                "id=" + id +
                ", kit=" + kit +
                ", user=" + user +
                '}';
    }
}
