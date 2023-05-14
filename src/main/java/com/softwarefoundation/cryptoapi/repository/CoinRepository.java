package com.softwarefoundation.cryptoapi.repository;

import com.softwarefoundation.cryptoapi.entity.Coin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@EnableAutoConfiguration
public class CoinRepository {

    private static String INSERT = "INSERT INTO TB01_COIN ( NAME, PRICE, QUANTITY, DATETIME) VALUES (?,?,?,?)";
    private static String SELECT_ALL = "SELECT NAME, SUM(QUANTITY) AS QUANTITY FROM TB01_COIN GROUP BY NAME";

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

    public List<Coin> getAll() {
        return this.jdbcTemplate.query(SELECT_ALL, new RowMapper<Coin>() {
            @Override
            public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coin coin = new Coin();
                coin.setName(rs.getString("NAME"));
                coin.setQuantity(rs.getBigDecimal("QUANTITY"));
                return coin;
            }
        });
    }
}
