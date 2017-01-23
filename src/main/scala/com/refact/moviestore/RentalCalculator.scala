package com.refact.moviestore

case class RentalCalculator(rentals:List[Rental]) {

  def totalAmount: Double = rentals.foldLeft(0.0)((acc, rental) => {
    acc + rental.movie.charge(rental.daysRented)
  })

  def renterPoints:Int = rentals.foldLeft(0)(_ + _.renterPoint)
}
