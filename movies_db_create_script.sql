CREATE DATABASE IF NOT EXISTS movie_db;

USE movie_db;

DROP TABLE IF EXISTS movies;

CREATE TABLE movies(
    title VARCHAR(100) NOT NULL,
    rating VARCHAR(10) NOT NULL,
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    genre VARCHAR(100),
    actors VARCHAR(100),
    director VARCHAR(100),
    plot TEXT,
    year INT UNSIGNED,
    poster TEXT,
    PRIMARY KEY (id)
);

INSERT INTO movies(title, rating, id, genre, actors, director, plot, year, poster) VALUES
(

)

INSERT INTO movies(title, rating) VALUES
(
 'Godzilla',
 '5'
)



