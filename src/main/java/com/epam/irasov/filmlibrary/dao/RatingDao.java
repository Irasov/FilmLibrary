package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Rating;

public interface RatingDao {
    Rating save (Rating rating);
    void upDate(Long idRating, int votes);
    void remove(Long id);
    Rating findByName(String name);
    Rating findById(Long id);
}
