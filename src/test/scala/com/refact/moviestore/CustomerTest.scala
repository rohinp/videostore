package com.refact.moviestore

import org.junit.{Assert, Before, Test}

class CustomerTest {

  var customer:Customer = Customer("Dinsdale Pirhana",List())

  var python = Movie("Monty Python and the Holy Grail", Movie.REGULAR)
  var ran = Movie("Ran", Movie.REGULAR)
  var la = Movie("LA Confidential", Movie.NEW_RELEASE)
  var trek = Movie("Star Trek 13.2", Movie.NEW_RELEASE)
  var wallace = Movie("Wallace and Gromit", Movie.CHILDRENS)

  @Before
  def setup(): Unit = {
    customer = customer.addRental(Rental(python, 3))
      .addRental(Rental(ran, 1))
      .addRental(Rental(la, 2))
      .addRental(Rental(trek, 1))
      .addRental(Rental(wallace, 6))
  }

  @Test
  def shouldGiveEmptyStatementForNoRentals():Unit =  {
    customer = Customer("Dinsdale Pirhana", List())
    equalsFile("1st Output", "outputEmpty", customer.statement())
  }

  @Test
  def shouldGiveStatementForGivenNewReleaseRegularAndChildrenMovies():Unit = {
    equalsFile("1st Output", "output1", customer.statement())
  }

  @Test
  def shouldGiveStatementForChangedMovieFromNewReleaseToRegular():Unit = {
    la = Movie("LA Confidential", Movie.REGULAR)

    customer = Customer("Dinsdale Pirhana",List()).addRental(Rental(python, 3))
      .addRental(Rental(ran, 1))
      .addRental(Rental(la, 2))
      .addRental(Rental(trek, 1))
      .addRental(Rental(wallace, 6))

    equalsFile("1st Output", "outputChange", customer.statement())
  }


  @Test
  def shouldTestTheGivenRentalsForHTMLStatement() = {
      equalsFile("1st Output", "outputHtml", customer.htmlStatement())
  }


  def equalsFile(message:String ,fileName:String ,actualValue:String ):Unit = {
    val lines: List[String] = scala.io.Source.fromResource(fileName).getLines().toList
    lines.zip(actualValue.split("\n").toList).foreach(t => {
      Assert.assertEquals("in file: " + fileName, t._1, t._2)
    })
  }

}
