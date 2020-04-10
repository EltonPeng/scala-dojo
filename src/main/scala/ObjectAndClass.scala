package classandobject

// * Companion object
class Person(val firstName: String, val lastName: String) {}

object Person {
  def apply(name: String): Person = {
    val names = name.split(" ")
    return new Person(names(0), names(1))
  }
}

// * Pattern Matching
case class Cat(color: String, food: String)

object Cat {
  def apply(color: String, food: String): Cat = {
    return new Cat(color, food)
  }
}

object ChipShop {
  def willServe(c: Cat): Boolean = {
    return c.food == "Chips"
  }
}
