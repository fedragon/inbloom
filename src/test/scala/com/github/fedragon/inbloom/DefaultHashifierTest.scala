package com.github.fedragon.inbloom

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DefaultHashifierTest extends FunSuite with BeforeAndAfter {

  var filter: BloomFilter = _

  before {
    filter = new BloomFilter(1)
  }

  test("hashifying `Hello` should give expected results") {
    assert(filter.hashify("Hello") === List(2, 10, 18, 26))
  }

  test("a small-sized filter will easily produce same results with different inputs") {
    val hello = filter.hashify("Hello")
    val dag = filter.hashify("Dag")

    assert(hello.equals(dag) === true)
  }

  test("a bigger-sized filter will reduce the probability of overlapping results") {
    val biggerFilter = new BloomFilter(2)
    val hello = biggerFilter.hashify("Hello")
    val ciao = biggerFilter.hashify("Ciao")

    assert(hello.equals(ciao) === false)
  }
}