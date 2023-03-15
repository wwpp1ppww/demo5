package com.example.demo.dao;

import com.example.demo.mapper.IngredientMapper;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.models.Ingredient;
import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngredientDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ingredient> index(){
        return jdbcTemplate.query("SELECT * FROM ingredients", new IngredientMapper());
    }

    public Ingredient show(int id) {
        return jdbcTemplate.query("SELECT * FROM ingredients WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Ingredient.class))
                .stream().findAny().orElse(null);
    }

    public void save(Ingredient ingredient) {
        jdbcTemplate.update("INSERT INTO ingredients VALUES(Default, ?)", ingredient.getName());


    }

}
