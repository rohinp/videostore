package com.refact.moviestore

class Movie(val title:String,val priceCode:Int) {
  def setPriceCode(pc: Int):Movie = Movie(title,pc)

  override def toString: String = "Movie ( " + title + " , " + priceCode + " )"
}

object Movie{

  def apply(title:String,priceCode:Int):Movie = new Movie(title,priceCode)

  val CHILDRENS:Int = 2
  val REGULAR:Int = 0
  val NEW_RELEASE:Int = 1
}
