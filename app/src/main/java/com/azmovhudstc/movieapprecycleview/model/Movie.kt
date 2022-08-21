package com.azmovhudstc.movieapprecycleview.model

class Movie {
    var movieName=""
    var movieDate=""
    var movieDescription=""
    var movieAuthor=""


    constructor(
        movieName: String,
        movieDate: String,
        movieDescription: String,
        movieAuthor: String
    ) {
        this.movieName = movieName
        this.movieDate = movieDate
        this.movieDescription = movieDescription
        this.movieAuthor = movieAuthor
    }
}