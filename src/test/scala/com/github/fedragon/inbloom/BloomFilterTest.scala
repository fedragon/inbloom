package com.github.fedragon.inbloom

import org.junit.runner.RunWith
import org.scalatest.{FunSuite, BeforeAndAfter}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BloomFilterTest extends FunSuite with BeforeAndAfter {
  
  var initial: BloomFilter = _

  before {
    initial = new BloomFilter()
  }

  test("should initially contain only zeros") {
    assert(initial.bits.forall(b => b == 0))
  }

  test("hashifying Hello should give expected results") {
    assert(new Hashify(20, 4).apply("Hello") === List(0, 5, 10, 15))
  }

  test("adding an element should return an updated array") {
    val hello = initial.add("Hello")
    assert(initial.equals(hello) === false)
  }

  test("querying an existing element should return true") {
    val hello = initial.add("Hello")
    assert(hello.query("Hello") === true)
  }

  test("querying a non-existing element should return false") {
    val hello = initial.add("Hello")
    assert(hello.query("Dag") === false)
  }

  test("querying a non-existing element might as well return true!") {
    val hello = 
      new BloomFilter(20, 4)
        .add("Hello")
    assert(hello.query("Ciao") === true)
  }
}
