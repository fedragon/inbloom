package com.github.fedragon.inbloom

import org.junit.runner.RunWith
import org.scalatest.{FunSuite, BeforeAndAfter}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountingFilterTest extends FunSuite with BeforeAndAfter {
  
  var empty: CountingFilter = _
  var dag: CountingFilter = _

  before {
    empty = new CountingFilter(1)
    dag = empty.add("Dag")
  }

  test("should initially contain only zeros") {
    assert(empty.bits.forall(b => b == 0))
  }

  test("adding an element should return an updated array") {
    assert(empty.equals(dag) === false)
  }

  test("when adding more than one element, some values might be bigger than 1") {
    val hello = empty.add("Hello")
    val dag = hello.add("Dag")

    assert(hello.bits.forall(n => n == 0 || n == 1) === true)
    assert(dag.bits.exists(n => n > 1) === true)
  }

  test("querying an existing element should return true") {
    assert(dag.contains("Dag") === true)
  }

  test("querying a non-existing element should return false") {
    assert(dag.contains("Ciao") === false)
  }

  test("querying a non-existing element might as well return true!") {
    val hello = empty.add("Hello")
    assert(hello.contains("Dag") === true)
  }

  test("deleting an element should decrease its associated positions") {
    val hello = empty.add("Hello")
    val ciao = hello.add("Dag")
    
    assert(ciao.bits.exists(n => n > 1) === true)
    assert(ciao.remove("Dag").bits.equals(hello.bits) === true)
  }
}
