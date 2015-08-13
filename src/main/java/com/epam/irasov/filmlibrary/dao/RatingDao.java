package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Rating;

public interface RatingDao {
    Rating save (Rating rating);
    void upDate();
    Rating findbyName(String name);
    Rating findbyId(Long id);
}
