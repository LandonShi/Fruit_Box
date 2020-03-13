package com.ssp.share.comparator;

import com.ssp.share.pojo.Kit;

import java.util.Comparator;

public class KitAllCompare implements Comparator<Kit> {


    @Override
    public int compare(Kit k1, Kit k2) {
        return k2.getDownloadTimes() * k2.getReviewCount() - k1.getDownloadTimes() * k1.getReviewCount();
    }
}
