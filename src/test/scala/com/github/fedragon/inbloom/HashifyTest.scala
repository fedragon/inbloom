package com.github.fedragon.inbloom

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HashifyTest extends FunSuite {

  test("hashifying Hello should give expected results") {
    assert(new Hashify(20, 4).apply("Hello") === List(0, 5, 10, 15))
  }

  test("a small size will easily produce same results with different inputs") {
    val hello = new Hashify(20, 4).apply("Hello")
    val ciao = new Hashify(20, 4).apply("Ciao")

    assert(hello.equals(ciao) === true)
  }

  test("a bigger size will reduce the probability of overlapping results") {
    val hello = new Hashify(200, 3).apply("Hello")
    val ciao = new Hashify(200, 3).apply("Ciao")

    assert(hello.equals(ciao) === false)
  }
}