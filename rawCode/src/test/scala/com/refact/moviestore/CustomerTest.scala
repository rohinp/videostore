package com.refact.moviestore

import org.junit.{Assert, Before, Test}

class CustomerTest {

  var dinsdale:Customer = Customer("Dinsdale Pirhana",List())

  var python = new Movie("Monty Python and the Holy Grail", Movie.REGULAR)
  var ran = new Movie("Ran", Movie.REGULAR)
  var la = new Movie("LA Confidential", Movie.NEW_RELEASE)
  var trek = new Movie("Star Trek 13.2", Movie.NEW_RELEASE)
  var wallace = new Movie("Wallace and Gromit", Movie.CHILDRENS)

  @Before
  def setup: Unit = {
    dinsdale = dinsdale.addRental(new Rental (python, 3))
      .addRental(new Rental (ran, 1))
      .addRental(new Rental (la, 2))
      .addRental(new Rental (trek, 1))
      .addRental(new Rental (wallace, 6))
  }

  @Test
  def testEmpty():Unit =  {
    dinsdale = new Customer("Dinsdale Pirhana")
    equalsFile("1st Output", "outputEmpty", dinsdale.statement())
  }

  @Test
  def testCustomer():Unit = {
    equalsFile("1st Output", "output1", dinsdale.statement())
  }

  @Test
  def testChange():Unit = {
    la = new Movie("LA Confidential", Movie.REGULAR)

    dinsdale = Customer("Dinsdale Pirhana",List()).addRental(new Rental (python, 3))
      .addRental(new Rental (ran, 1))
      .addRental(new Rental (la, 2))
      .addRental(new Rental (trek, 1))
      .addRental(new Rental (wallace, 6))

    equalsFile("1st Output", "outputChange", dinsdale.statement());
  }

  /*
  @Test
  def shouldTestTheGivenRentalsForHTMLStatement() = {
      equalsFile("1st Output", "outputHtml", customer.htmlStatement())
  }
  */

  def equalsFile(message:String ,fileName:String ,actualValue:String ):Unit = {
    val lines: List[String] = scala.io.Source.fromResource(fileName).getLines().toList
    lines.zip(actualValue.split("\n").toList).foreach(t => {
      Assert.assertEquals("in file: " + fileName, t._1, t._2)
    })
  }

}
