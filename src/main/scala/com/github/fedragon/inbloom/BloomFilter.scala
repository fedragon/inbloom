package com.github.fedragon.inbloom

class BloomFilter private[inbloom] (b: Vector[Int], r: Int, h: Hashify) extends BaseFilter(b, r, h) {
  
  private[inbloom] def this(bits: Vector[Int], ratio: Int) = 
    this(bits, ratio, new Hashify(bits.size, ratio))

  def this(size: Int) = {
    this((0 until size).map(_ => 0).toVector, 4, new Hashify(size, 4))    
  }

  def add(value: String): BloomFilter = {

    def add0(index: Int, vector: Vector[Int]) = {
      val current = vector(index)

      if(current == 0)
        vector.updated(index, 1)
      else vector
    }

    new BloomFilter(update(value, add0), ratio)
  }
}
