package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Review;

public interface ReviewDao {
    Review findById(Long id);
    Long findId();
    void save(Review review);
    void saveFilmReview(Long idFilm,Long idReview);
    void saveSystemMemberReview(Long idMember, Long idReview);
}
