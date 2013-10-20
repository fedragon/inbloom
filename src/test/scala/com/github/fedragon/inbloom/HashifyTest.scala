package com.github.fedragon.inbloom

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HashifyTest extends FunSuite with BeforeAndAfter {

  var filter: BloomFilter = _

  before {
    filter = new BloomFilter(20)
  }

  test("hashifying `Hello` should give expected results") {
    assert(filter.hashify("Hello") === List(0, 5, 10, 15))
  }

  test("a small size will easily produce same results with different inputs") {
    val hello = filter.hashify("Hello")
    val ciao = filter.hashify("Ciao")

    assert(hello.equals(ciao) === true)
  }

  test("a bigger size will reduce the probability of overlapping results") {
    val biggerFilter = new BloomFilter(200)
    val hello = biggerFilter.hashify("Hello")
    val ciao = biggerFilter.hashify("Ciao")

    assert(hello.equals(ciao) === false)
  }
}