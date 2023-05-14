package com.softwarefoundation.cryptoapi.entity;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
public class Coin implements Serializable {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal quantity;
    private Timestamp dateTime;

}
