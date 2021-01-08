package com.danylko.yourburger.service.jdbc;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceJDBCImpl implements ProductService {

    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    public ProductServiceJDBCImpl(NamedParameterJdbcTemplate parameterJdbcTemplate) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from products";
        return parameterJdbcTemplate.query( sql,
                (rs, rowNum) ->
                new Product(rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("description"),
                        rs.getInt("price")));
    }

    @Override
    public Product findById(Long id) {
        String sql = "select * from products where prod_id = :id";
        return parameterJdbcTemplate.queryForObject( sql,
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        new Product(rs.getString("name"),
                                rs.getString("image"),
                                rs.getString("description"),
                                rs.getInt("price")));
    }

    @Override
    public Product findByName(String name) {
        String sql = "select * from products where name = :name";
        return parameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource("name", name),
                (rs, rowNum) ->
                        new Product(rs.getString("name"),
                                rs.getString("image"),
                                rs.getString("description"),
                                rs.getInt("price")));
    }

    @Override
    public void save(Product product) {
        String sql = "insert into products (name, image, description, price) " +
                "values (:name, :image, :description, :price) ";
        parameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(product));
    }

    @Override
    public void delete(Product product) {
        String sql = "delete from products where prod_id = :id";
        parameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", product.getProdId()));

    }
}
