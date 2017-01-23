package com.refact.moviestore


trait Movie {
  val title:String
  val priceCode:Int
  def charge(daysRented:Int):Double
  def changePriceCode(pc: Int):Movie = Movie(title,pc)
}

case class RegularMovie(title:String, priceCode:Int) extends Movie {
  override def charge(daysRented: Int): Double = 2.0 + (if (daysRented > 2) (daysRented - 2) * 1.5
  else 0)
}

case class NewRelease(title:String, priceCode:Int) extends Movie {
  override def charge(daysRented: Int): Double = daysRented * 3.0
}

case class ChildrenMovie(title:String, priceCode:Int) extends Movie {
  override def charge(daysRented: Int): Double = 1.5 + (if (daysRented > 3) (daysRented - 3) * 1.5
  else 0)
}


object Movie{

  val CHILDRENS:Int = 2
  val REGULAR:Int = 0
  val NEW_RELEASE:Int = 1

  def apply(title:String,priceCode:Int):Movie = priceCode match {
    case Movie.REGULAR => RegularMovie(title,priceCode)
    case Movie.NEW_RELEASE => NewRelease(title,priceCode)
    case Movie.CHILDRENS => ChildrenMovie(title,priceCode)
  }
}
