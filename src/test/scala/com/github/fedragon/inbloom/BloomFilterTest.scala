package com.github.fedragon.inbloom

import org.junit.runner.RunWith
import org.scalatest.{FunSuite, BeforeAndAfter}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BloomFilterTest extends FunSuite with BeforeAndAfter {
  
  var empty: BloomFilter = _
  var dag: BloomFilter = _

  before {
    empty = new BloomFilter(200)
    dag = empty.add("Dag")
  }

  test("should initially contain only zeros") {
    assert(empty.bits.forall(b => b == 0))
  }

  test("adding an element should return an updated array") {
    assert(empty.equals(dag) === false)
    assert(dag.bits.exists(n => n == 1) === true)
  }

  test("querying an existing element should return true") {
    assert(dag.query("Dag") === true)
  }

  test("querying a non-existing element should return false") {
    assert(dag.query("Hello") === false)
  }

  test("querying a non-existing element might as well return true!") {
    val hello = 
      new BloomFilter(20)
        .add("Hello")
    assert(hello.query("Ciao") === true)
  }
}
