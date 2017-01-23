package com.refact.moviestore


case class Customer(name:String,var rentals:List[Rental]) {

  def this(n:String) = this(n,List())

  def statement():String = {
    var totalAmount:Double = 0.0
    var frequentRenterPoints = 0

    var result = "Rental Record for " + name + "\n"

    rentals.foreach( each =>  {
      var thisAmount:Double = 0.0

      //determine amounts for each line
      each.movie.priceCode match {

        case Movie.REGULAR =>
          thisAmount += 2.0
          if (each.daysRented > 2)
            thisAmount += (each.daysRented - 2) * 1.5
        case Movie.NEW_RELEASE =>
          thisAmount += each.daysRented * 3.0
        case Movie.CHILDRENS =>
          thisAmount += 1.5
          if (each.daysRented > 3)
            thisAmount += (each.daysRented - 3) * 1.5

      }
        // add frequent renter points
        frequentRenterPoints += 1;
        // add bonus for a two day new release rental
        if ((each.movie.priceCode == Movie.NEW_RELEASE)
          &&
          each.daysRented > 1) frequentRenterPoints += 1;

          //show figures
        result += "\t" + each.movie.title + "\t" + thisAmount + "\n"
        totalAmount += thisAmount
    })

      //add footer lines
      result += "Amount owed is " + totalAmount + "\n"
      result += "You earned " + frequentRenterPoints + " frequent renter points"
      result
  }

  def addRental(rental: Rental): Customer = Customer(name,rentals ++ List(rental) )
}
