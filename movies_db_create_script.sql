CREATE DATABASE IF NOT EXISTS movie_db;

USE movie_db;

DROP TABLE IF EXISTS movies;

CREATE TABLE movies(
    title VARCHAR(100) NOT NULL,
    rating VARCHAR(10) NOT NULL,
    id INT UNSIGNED NOT NULL,
    genre VARCHAR(100) NOT NULL,
    actors VARCHAR(100) NOT NULL,
    director VARCHAR(100) NOT NULL,
    plot TEXT NOT NULL,
    year INT UNSIGNED NOT NULL,
    poster TEXT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO movies(title, rating, id, genre, actors, director, plot, year, poster) VALUES
(
    'Black Hawk Down',
   '5',
    1,
    'War, Action',
    'Josh Hartnett, Ewan McGregor, Tom Sizemore, Eric Bana',
   'Ridley Scott',
    '160 elite U.S. soldiers drop into Somalia to capture two top lieutenants of a renegade warlord and find themselves in a desperate battle with a large force of heavily-armed Somalis.',
    '1990',
 'https://m.media-amazon.com/images/M/MV5BYWMwMzQxZjQtODM1YS00YmFiLTk1YjQtNzNiYWY1MDE4NTdiXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SX300.jpg'
)



