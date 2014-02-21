package com.github.fedragon.inbloom

import org.junit.runner.RunWith
import org.scalatest.{FunSuite, BeforeAndAfter}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FilterMonoidTest extends FunSuite with BeforeAndAfter {
  
  var fm: FilterMonoid = _
  
  before {
    fm = new FilterMonoid {
      val bits = Vector(0)
    }
  }

  test("should verify the identity rule") {
    assert(fm.append(fm.bits, fm.zero) === fm.append(fm.zero, fm.bits))
  }

  test("should verify the associativity rule") {
    val zeros = Vector(0)
    val ones = Vector(1)
    val twos = Vector(2)

    assert(fm.append(zeros, fm.append(ones, twos)) === fm.append(fm.append(zeros, ones), twos))
  }
}
