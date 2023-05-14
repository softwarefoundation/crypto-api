package com.softwarefoundation.cryptoapi.repository;

import com.softwarefoundation.cryptoapi.entity.Coin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@EnableAutoConfiguration
public class CoinRepository {

    private static String INSERT = "INSERT INTO TB01_COIN ( NAME, PRICE, QUANTITY, DATETIME) VALUES (?,?,?,?)";

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
