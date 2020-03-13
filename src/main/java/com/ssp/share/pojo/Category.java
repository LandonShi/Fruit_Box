package com.ssp.share.pojo;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
 
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   
    int id;
    String name;

    @Transient
    List<Kit> kits;  //一个分类下有多个工具
    @Transient
    List<List<Kit>> kitsByRow;

    public List<Kit> getKits() {
        return kits;
    }

    public void setKits(List<Kit> kits) {
        this.kits = kits;
    }

    public List<List<Kit>> getKitsByRow() {
        return kitsByRow;
    }

    public void setKitsByRow(List<List<Kit>> kitsByRow) {
        this.kitsByRow = kitsByRow;
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
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
    
    
}