package com.example.demo.mapper;

import com.example.demo.models.Ingredient;
import com.example.demo.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet resultSet, int i) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(resultSet.getInt("id"));
        ingredient.setName(resultSet.getString("name"));

        return ingredient;

    }
}
