package com.example.demo.dao;

import com.example.demo.mapper.IngredientMapper;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.models.Ingredient;
import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

    public Ingredient save(Ingredient ingredient) {
        String INSERT_MESSAGE_SQL = "INSERT INTO ingredients VALUES(Default, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ingredient.getName());
            return ps;
        }, keyHolder);
        int id = (int) keyHolder.getKeys().get("id");
        ingredient.setId(id);
        return ingredient;

    }

    public Ingredient update(Ingredient updatedIngredient) {
        jdbcTemplate.update("UPDATE ingredients SET name=? WHERE id=?", updatedIngredient.getName(), updatedIngredient.getId());
        return updatedIngredient;
    }

}
