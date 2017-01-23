package com.refact.moviestore

abstract class Statement[T] extends (() => T){
  def header:T
  def body:T
  def footer:T
}

case class TextStatement(custName: String, rentals: List[Rental], rentalCalc: RentalCalculator) extends Statement[Text] {
  override def header: Text = "Rental Record for " + custName + "\n"

  override def body: Text = rentals.foldLeft("")((acc,rental) => {
    acc + "\t" + rental.movie.title + "\t" + rental.movie.charge(rental.daysRented) + "\n"
  })

  override def footer: Text =
    "Amount owed is " + rentalCalc.totalAmount + "\n" +
      "You earned " + rentalCalc.renterPoints + " frequent renter points"

  override def apply(): Text = header + body + footer
}

case class HTMLStatement(custName: String, rentals: List[Rental], rentalCalc: RentalCalculator) extends Statement[HTML] {

  override def header: HTML = "<H1>Rentals for <EM>" + custName +"</EM></H1>"

  override def body: HTML = rentals.foldLeft("<P>")((acc,rental) => {
    acc  + rental.movie.title + ": " + rental.movie.charge(rental.daysRented) + "<BR>"
  })

  override def footer: HTML = "<P>You owe <EM>" + rentalCalc.totalAmount + "</EM><P>On this rental you earned <EM>" + rentalCalc.renterPoints +"</EM> frequent renter points<P>"

  override def apply(): Text = header + body + footer
}

object Statement {
  def text(name: String, rentals: List[Rental], rentalCalc: RentalCalculator): Text =
    TextStatement(name,rentals,rentalCalc)()
  def html(name: String, rentals: List[Rental], rentalCalc: RentalCalculator): HTML =
    HTMLStatement(name: String, rentals: List[Rental], rentalCalc: RentalCalculator)()
}