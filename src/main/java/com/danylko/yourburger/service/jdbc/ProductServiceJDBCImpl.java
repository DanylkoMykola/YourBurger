package com.danylko.yourburger.service.jdbc;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Qualifier("productServiceJDBC")
public class ProductServiceJDBCImpl implements ProductService {

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    public ProductServiceJDBCImpl(NamedParameterJdbcTemplate parameterJdbcTemplate) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM PRODUCTS";
        return parameterJdbcTemplate.query( sql,
                (rs, rowNum) ->
                new Product( rs.getLong("prod_id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("description"),
                        rs.getInt("price")));
    }

    @Override
    public Product findById(Long id) {
        String sql = "SELECT * FROM PRODUCTS WHERE PROD_ID = :ID";
        return parameterJdbcTemplate.queryForObject( sql,
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        new Product( rs.getLong("prod_id"),
                                rs.getString("name"),
                                rs.getString("image"),
                                rs.getString("description"),
                                rs.getInt("price")));
    }

    @Override
    public Product findByName(String name) {
        String sql = "SELECT * FROM PRODUCTS WHERE NAME = :NAME";
        return parameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource("name", name),
                (rs, rowNum) ->
                        new Product( rs.getLong("prod_id"),
                                rs.getString("name"),
                                rs.getString("image"),
                                rs.getString("description"),
                                rs.getInt("price")));
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO PRODUCTS (NAME, IMAGE, DESCRIPTION, PRICE) " +
                "VALUES (:NAME, :IMAGE, :DESCRIPTION, :PRICE) ";
        parameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(product));
    }

    @Override
    public void delete(Product product) {
        String sql = "DELETE FROM PRODUCTS WHERE PROD_ID = :ID";
        parameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", product.getProdId()));

    }

    @Override
    public void checkEmptyFields(Product product, String name, String description, String price, MultipartFile image) {
        throw new RuntimeException("Unsupported operation!");
    }
}
