package com.ssp.share.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "kit")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Kit implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	private String name;  			//工具标题
    private String address;  		//工具在服务器上的路径
    private String icon;  			//工具图标
    private String flag;  			//视频的转载、原创之类
    private Integer downloadTimes;  //下载次数
    private String description;  	//简介
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date createTime;  		//创建时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;  		//更新时间

    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    @Transient
    private KitImage firstKitImage;

    @Transient
    private List<KitImage> kitSingleImages;

    @Transient
    private List<KitImage> kitDetailImages;

    @Transient
    private int reviewCount;  //累计评价

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<KitImage> getKitSingleImages() {
        return kitSingleImages;
    }

    public void setKitSingleImages(List<KitImage> kitSingleImages) {
        this.kitSingleImages = kitSingleImages;
    }

    public List<KitImage> getKitDetailImages() {
        return kitDetailImages;
    }

    public void setKitDetailImages(List<KitImage> kitDetailImages) {
        this.kitDetailImages = kitDetailImages;
    }

    public KitImage getFirstKitImage() {
        return firstKitImage;
    }

    public void setFirstKitImage(KitImage firstKitImage) {
        this.firstKitImage = firstKitImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Kit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", icon='" + icon + '\'' +
                ", flag='" + flag + '\'' +
                ", downloadTimes=" + downloadTimes +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", category=" + category +
                ", user=" + user +
                ", firstKitImage=" + firstKitImage +
                ", kitSingleImages=" + kitSingleImages +
                ", kitDetailImages=" + kitDetailImages +
                ", reviewCount=" + reviewCount +
                '}';
    }
}
