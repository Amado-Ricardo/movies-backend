fetch("http://localhost:8080/movies", {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    redirect: 'follow',
    body: JSON.stringify([{
        "title": "Pootie Tang",
        "rating": "5",
        "id": 8,
        "genre": "Comedy, Action",
        "actors": "Lance Crouther, Chris Rock, Wanda Sykes",
        "director": "Louis C. K.",
        "plot": "\"The Man\" is baffled by a chill hero's invincible weapon: gibberish.",
        "year": "2001",
        "poster": "https://images-na.ssl-images-amazon.com/images/I/51ahSJ4EZ5L._AC_.jpg"
    }])
}).then(function(response) {
    return response.json();
}).then(function(data) {
    console.log(data);
});



//*********************************************************



fetch("http://localhost:8080/movies", {
    method: 'DELETE',
    headers: {
        'Content-Type': 'application/json'
    },
    redirect: 'follow',
    body: JSON.stringify(8)
}).then(function(response) {
    return response.json();
}).then(function(data) {
    console.log(data);
});


//************************************************************


fetch("http://localhost:8080/movies", {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
    redirect: 'follow',
    body: JSON.stringify({
        "title": "Resident Evil 22",
        "rating": "5",
        "poster": "https://m.media-amazon.com/images/M/MV5BZmI1ZGRhNDYtOGVjZC00MmUyLThlNTktMTQyZGE3MzE1ZTdlXkEyXkFqcGdeQXVyNDE5MTU2MDE@._V1_SX300.jpg",
        "year": "2002",
        "genre": "Action, Horror, Sci-Fi",
        "director": "Paul W.S. Anderson",
        "plot": "A special military unit fights a powerful, out-of-control supercomputer and hundreds of scientists who have mutated into flesh-eating creatures after a laboratory accident.",
        "actors": "Ryan McCluskey, Oscar Pearce, Indra Ové, Anna Bolt",
        "id": 3
    })
}).then(function(response) {
    return response.json();
}).then(function(data) {
    console.log(data);
});

//********************************************************

fetch("http://localhost:8080/movies", {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    },
    redirect: 'follow'
}).then(function(response) {
    return response.json();
}).then(function(data) {
    console.log(data);
});