package org.example.pojo;

import lombok.Data;

@Data
public class User {

    private String id;

    private int commentNum;

    private int readingNum;

    private int storeNum;

    private int favourNum;
}
