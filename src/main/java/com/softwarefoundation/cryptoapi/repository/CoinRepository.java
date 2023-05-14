package com.softwarefoundation.cryptoapi.repository;

import com.softwarefoundation.cryptoapi.entity.Coin;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

public class CoinRepository {

    private static String INSERT = "INSERT INTO COIN ( NAME, PRICE, QUANTITY, DATETIME) VALEUS (?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    public CoinRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Coin insert(Coin coin) {
        Object[] attr = new Object[]{
                coin.getName(),
                coin.getPrice(),
                coin.getQuantity(),
                coin.getDateTime()
        };
        this.jdbcTemplate.update(INSERT, attr);
        return coin;
    }
}
