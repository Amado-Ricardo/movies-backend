fetch("http://localhost:8080/movies", {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    redirect: 'follow',
    body: JSON.stringify([{
        "title": "Black Hawk Down",
        "rating": "5",
        "id": 1,
        "genre": "War, Action",
        "actors": "Josh Hartnett, Ewan McGregor, Tom Sizemore, Eric Bana",
        "director": "Ridley Scott",
        "plot": "160 elite U.S. soldiers drop into Somalia to capture two top lieutenants of a renegade warlord and find themselves in a desperate battle with a large force of heavily-armed Somalis.",
        "year": "1990",
        "poster": "https://m.media-amazon.com/images/M/MV5BYWMwMzQxZjQtODM1YS00YmFiLTk1YjQtNzNiYWY1MDE4NTdiXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SX300.jpg"
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
    body: JSON.stringify(3)
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