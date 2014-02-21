package com.github.fedragon.inbloom

class BloomFilter private[inbloom](val bits: Vector[Int]) extends Hashifier with DefaultHashifier {

  def this(size: Int) = {
    this((0 until size * 32).map(_ => 0).toVector)
  }

  def contains(value: String): Boolean = {
    hashify(value).map(hash => bits(hash)).forall(b => b != 0)
  }

  def add(value: String): BloomFilter = {

    def addFunc(index: Int, vector: Vector[Int]) = {
      val current = vector(index)

      if(current == 0) vector.updated(index, 1)
      else vector
    }

    new BloomFilter(updateBits(value, addFunc))
  }

  private[inbloom] def updateBits(value: String, func: (Int, Vector[Int]) => Vector[Int]): Vector[Int] = {

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
