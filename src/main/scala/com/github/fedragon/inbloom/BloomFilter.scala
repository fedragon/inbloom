package com.github.fedragon.inbloom

class BloomFilter (b: Vector[Int], h: Hashify) extends BaseFilter(b, h) {

  def this(bits: Vector[Int], ratio: Int) = 
    this(bits, new Hashify(bits.size, ratio))

  def this(size: Int) = {
    this((0 until size).map(_ => 0).toVector, new Hashify(size, BaseFilter.DefaultRatio))    
  }

  def add(value: String): BloomFilter = {

    def add0(index: Int, vector: Vector[Int]) = {
      val current = vector(index)

      if(current == 0)
        vector.updated(index, 1)
      else vector
    }

    new BloomFilter(update(value, add0), hashify)
  }
}
