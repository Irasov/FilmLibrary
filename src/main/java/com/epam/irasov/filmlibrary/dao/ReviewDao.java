package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Review;

import java.util.List;

public interface ReviewDao {
    Review findById(Long id);
    Long findId();
    void save(Review review);
    void saveFilmReview(Long idFilm,Long idReview);
    void saveSystemMemberReview(Long idMember, Long idReview);
    boolean emptyTable();
    boolean emptyPublished();
    List<Review> selectReviews();
    void upDate(Long id,boolean published);
    List<Review> selectEmptyReviews();
    void remove(Long id);
    List<Long> emptyMyReview(Long id);
    void upDateText(Long id, String text);
    Long findIdRating(Long id);
}
