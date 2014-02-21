package com.github.fedragon.inbloom

class CountingFilter private[inbloom](bits: Vector[Int]) extends BloomFilter(bits) {

  def this(size: Int) = {
    this((0 until size * 32).map(_ => 0).toVector)
  }

  override def add(value: String): CountingFilter = {

    def addFunc(index: Int, vector: Vector[Int]) = {
      val current = vector(index)

      if(current < Int.MaxValue) vector.updated(index, vector(index) + 1)
      else vector
    }

    new CountingFilter(updateBits(value, addFunc))
  }

  def remove(value: String): CountingFilter = {

    def removeFunc(index: Int, vector: Vector[Int]) = {
      val current = vector(index)

      if(current > 0)
        vector.updated(index, current - 1)
      else vector
    }

    new CountingFilter(updateBits(value, removeFunc))
  }
}
