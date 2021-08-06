CREATE DATABASE IF NOT EXISTS movie_db;

USE movie_db;

DROP TABLE IF EXISTS movies;

CREATE TABLE movies(
    title VARCHAR(100) NOT NULL,
    rating INT NOT NULL,
    id INT UNSIGNED NOT NULL,
    genre VARCHAR(100) NOT NULL,
    actors VARCHAR(100) NOT NULL,
    director VARCHAR(100) NOT NULL,
    plot TEXT NOT NULL,
    year INT UNSIGNED NOT NULL,
    poster TEXT NOT NULL,
    PRIMARY KEY (id)
);



