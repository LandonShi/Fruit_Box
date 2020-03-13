package com.ssp.share.comparator;

import com.ssp.share.pojo.Kit;

import java.util.Comparator;

public class KitDateCompare implements Comparator<Kit> {


    @Override
    public int compare(Kit k1, Kit k2) {
        return k1.getUpdateTime().compareTo(k2.getUpdateTime());
    }
}
