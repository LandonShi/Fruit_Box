package com.ssp.share.text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class sss {

    public static void main(String[] args) {


        DateTimeFormatter df4=DateTimeFormatter.ofPattern("y-M-d HH:mm:ss");
        LocalDateTime loginDate = LocalDateTime.now();

        System.out.println(df4.format(loginDate));
    }
}
