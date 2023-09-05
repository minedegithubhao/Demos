package org.example.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author cxdpc
 * @date 2023-09-03 21:04
 */
@Data
public class Account {
    private long id;
    private String ownerName;
    private double balance;
    private Date accessTime;
    private boolean locked;
}
