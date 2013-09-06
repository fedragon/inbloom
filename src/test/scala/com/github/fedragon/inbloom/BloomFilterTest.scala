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

  test("adding an element should return an updated BloomFilter") {
    val updated = initial.add("Hello")

    assert(initial.equals(updated) === false)
  }

  test("query an existing element should return true") {
    val updated = initial.add("Hello")

    assert(updated.query("Hello") === true)
  }

  test("query a non-existing element might return false") {
    val hello = initial.add("Hello")
    assert(hello.query("Dag") === false)
  }
}
