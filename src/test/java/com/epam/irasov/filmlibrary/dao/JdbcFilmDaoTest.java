package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.Rating;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JdbcFilmDaoTest {
    private static AtomicBoolean stop = new AtomicBoolean(false);
    private static DaoFactory daoFactory;
    private static AtomicInteger countFilm;
    private  Rating rating;

    @Before
    public void setUp() throws Exception {
        countFilm = new AtomicInteger();
        daoFactory = DaoFactory.getInstance();
        rating = new Rating(55l,"rating",0);
        RatingDao ratingDao = daoFactory.newRatingDao();
        ratingDao.save(rating);
        System.out.println("Start test!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("End test!");
    }

    @Test
    public void shouldInsert() throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    daoFactory.beginTx();
                    FilmDao filmDao = daoFactory.newFilmDao();
                    Film film = new Film(1L, "film","tag line","comedy",LocalDate.now(),18,108,"img/site/no_avatar.png",rating,"description");
                    filmDao.save(film);
                    daoFactory.endTx();
                    countFilm.incrementAndGet();
                }

            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) threads[i] = new Thread(runnable);
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        assertThat(countFilm.get(),is(10000));
        System.out.println("count films: " + countFilm);
    }
}
