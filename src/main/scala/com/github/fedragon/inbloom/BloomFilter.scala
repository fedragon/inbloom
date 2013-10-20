package com.github.fedragon.inbloom

class BloomFilter (val bits: Vector[Int]) extends Hashifier[BloomFilter] with DefaultHashifier[BloomFilter] {
  require(bits.size > 0)

  def this(size: Int) = {
    this((0 until size).map(_ => 0).toVector)
  }

  def query(value: String) = 
    hashify(value)
      .map(hash => bits(hash))
        .forall(b => b == 1)

  private[inbloom] def update(value: String, func: (Int, Vector[Int]) => Vector[Int]): Vector[Int] = {

    def update0(hashes: List[Int], result: Vector[Int]): Vector[Int] = {
      hashes match {
        case Nil => result
        case head :: tail => 
          update0(tail, func(head, result))
      }
    }

    update0(hashify(value), bits)
  }

  def add(value: String): BloomFilter = {

    def add0(index: Int, vector: Vector[Int]) = {
      val current = vector(index)

      if(current == 0)
        vector.updated(index, 1)
      else vector
    }

    new BloomFilter(update(value, add0))
  }
}
