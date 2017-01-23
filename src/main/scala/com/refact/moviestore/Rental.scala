package com.refact.moviestore

case class Rental(movie: Movie,daysRented:Int) {
  def renterPoint:Int = 1 + (if(movie.priceCode == Movie.NEW_RELEASE && daysRented > 1) 1 else 0)
}
