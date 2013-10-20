package com.github.fedragon.inbloom

import org.junit.runner.RunWith
import org.scalatest.{FunSuite, BeforeAndAfter}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountingFilterTest extends FunSuite with BeforeAndAfter {
  
  var empty: CountingFilter = _
  var dag: CountingFilter = _

  before {
    empty = new CountingFilter(200)
    dag = empty.add("Dag")
  }

  test("should initially contain only zeros") {
    assert(empty.bits.forall(b => b == 0))
  }

  test("adding an element should return an updated array") {
    assert(empty.equals(dag) === false)
  }

  test("when adding more than one element, some values might be bigger than 1") {
    val initial = new CountingFilter(20)
    val hello = initial.add("Hello")
    val ciao = hello.add("Ciao")

    assert(hello.bits.forall(n => n == 0 || n == 1) === true)
    assert(ciao.bits.exists(n => n > 1) === true)
  }

  test("querying an existing element should return true") {
    assert(dag.query("Dag") === true)
  }

  test("querying a non-existing element should return false") {
    assert(dag.query("Hello") === false)
  }

  test("querying should work even when some positions are bigger than 1") {
    val ciao = new CountingFilter(20).add("Hello").add("Ciao")

    assert(ciao.query("Ciao") === true)
    assert(ciao.query("Dag") === false)
  }

  test("querying a non-existing element might as well return true!") {
    val hello = new CountingFilter(20).add("Hello")
    assert(hello.query("Ciao") === true)
  }

  test("deleting an element should decrease its associated positions") {
    val initial = new CountingFilter(20)
    val hello = initial.add("Hello")
    val ciao = hello.add("Ciao")
    
    assert(ciao.bits.exists(n => n > 1) === true)
    assert(ciao.delete("Ciao").bits.equals(hello.bits) === true)
  }
}
