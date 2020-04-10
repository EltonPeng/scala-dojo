package typeclass

sealed trait TrafficLight {
  def next: TrafficLight = this match {
    case Red    => Green
    case Green  => Yellow
    case Yellow => Red
  }
}

final case object Red extends TrafficLight
final case object Green extends TrafficLight
final case object Yellow extends TrafficLight

sealed trait LinkedList[A] {
  def apply(index: Int): A = this match {
    case Pair(head, tail) => {
      if (index == 0) {
        return head
      } else {
        return tail(index - 1)
      }
    }
    case End() => {
      throw new Exception("Out of Boundary")
    }
  }
}

final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

sealed trait CoLinkedList[+A] {
  def apply(index: Int): A = this match {
    case p: CoPair[A] => {
      if (index == 0) {
        return p.head
      } else {
        return p.tail(index - 1)
      }
    }
    case CoEnd => {
      throw new Exception("Out of Boundary")
    }
  }
}
final case class CoPair[A](head: A, tail: CoLinkedList[A])
    extends CoLinkedList[A]
final case object CoEnd extends CoLinkedList[Nothing]

trait JsonWriter[A] {
  def write(in: A): String
}

object JsonWriter {
  def write[A](a: A): String = ???
  implicit class Ops[A](a: A) {
    def writeJson: String = ???
  }
}

final case class Person(name: String, email: String)

object Person {
  implicit val jsonWriterForPerson: JsonWriter[Person] = ???
  // hint: https://www.scala-lang.org/api/current/scala/math/Ordering$.html#fromLessThan[T](cmp:(T,T)=%3EBoolean):scala.math.Ordering[T]
  implicit val sortablePerson: Ordering[Person] = ???
}

final case class Cat(name: String, food: String)

object Cat {
  implicit val jsonWriterForCat: JsonWriter[Cat] = ???
}

final case class CatPerson(person: Person, cat: Cat)

object CatPerson {
  implicit val jsonWriterForCatPerson: JsonWriter[CatPerson] = ???
  implicit class CatPersonOps(cp: CatPerson) {
    def writeJson: String = ???
  }
}
