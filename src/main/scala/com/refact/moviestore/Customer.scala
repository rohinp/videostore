package com.refact.moviestore


case class Customer(name:String,rentals:List[Rental]) {

  def statement():Text = Statement.text(name,rentals,RentalCalculator(rentals))

  def htmlStatement():HTML = Statement.html(name,rentals,RentalCalculator(rentals))

  def addRental(rental: Rental): Customer = Customer(name,rentals ++ List(rental) )
}
