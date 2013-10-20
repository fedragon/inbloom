package com.github.fedragon.inbloom

class BloomFilter protected(val bits: Vector[Int]) extends Hashifier with DefaultHashifier {
  require(bits.size > 0)

  def this(size: Int) = {
    this((0 until size).map(_ => 0).toVector)
  }

  def query(value: String): Boolean = {
    hashify(value).map(hash => bits(hash)).forall(b => b == 1)
  }

  def add(value: String): BloomFilter = {

    def addFunc(index: Int, vector: Vector[Int]) = {
      val current = vector(index)

      if(current == 0)
        vector.updated(index, 1)
      else vector
    }

    new BloomFilter(updateBits(value, addFunc))
  }

  protected def updateBits(value: String, func: (Int, Vector[Int]) => Vector[Int]): Vector[Int] = {

    @scala.annotation.tailrec
    def update(hashes: List[Int], result: Vector[Int]): Vector[Int] = {
      hashes match {
        case Nil => result
        case head :: tail => 
          update(tail, func(head, result))
      }
    }

    update(hashify(value), bits)
  }
}
