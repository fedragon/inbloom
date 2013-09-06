package com.github.fedragon.inbloom

class CountingFilter private[inbloom] (b: Vector[Int], r: Int, h: Hashify) extends BaseFilter(b, r, h) {

  private[inbloom] def this(bits: Vector[Int], ratio: Int) = 
    this(bits, ratio, new Hashify(bits.size, ratio))

  def this(size: Int) = {
    this((0 until size).map(_ => 0).toVector, 4, new Hashify(size, 4))    
  }

  def add(value: String): CountingFilter = {

    def add0(index: Int, vector: Vector[Int]) = 
      vector.updated(index, vector(index) + 1)

    new CountingFilter(update(value, add0), ratio)
  }

  def delete(value: String): CountingFilter = {

    def delete0(index: Int, vector: Vector[Int]) = {
      val current = vector(index)

      if(current > 0)
        vector.updated(index, current - 1)
      else vector
    }

    new CountingFilter(update(value, delete0), ratio)
  }
}
