package com.github.fedragon.inbloom

class CountingFilter (b: Vector[Int]) extends BaseFilter(b) {

  def this(size: Int) = {
    this((0 until size).map(_ => 0).toVector)
  }

  override def query(value: String) = 
    hashify(value)
      .map(hash => bits(hash))
        .forall(b => b > 0)

  def add(value: String): CountingFilter = {

    def add0(index: Int, vector: Vector[Int]) = 
      vector.updated(index, vector(index) + 1)

    new CountingFilter(update(value, add0))
  }

  def delete(value: String): CountingFilter = {

    def delete0(index: Int, vector: Vector[Int]) = {
      val current = vector(index)

      if(current > 0)
        vector.updated(index, current - 1)
      else vector
    }

    new CountingFilter(update(value, delete0))
  }
}
