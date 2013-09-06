package com.github.fedragon.inbloom

case class BloomFilter(bits: Vector[Int], ratio: Int) {
  
  private val hashify = new Hashify(bits.size, ratio)

  def this(size: Int, ratio: Int) = {
    this((0 until size).map(_ => 0).toVector, ratio)
  }

  def this() = this(20, 4)

  def add(value: String): BloomFilter = {

    def add0(hashes: List[Int], result: Vector[Int]): Vector[Int] = {
      hashes match {
        case Nil => result
        case head :: tail => add0(tail, result.updated(head, 1))
      }
    }

    BloomFilter(add0(hashify(value), bits), ratio)
  }

  def query(value: String) = 
    hashify(value)
      .map(hash => bits(hash))
        .forall(b => b == 1)
}

private[inbloom] class Hashify(size: Int, ratio: Int) {

  def apply(value: String): List[Int] = {
    val modulo = size / ratio
    val hash = value.hashCode % modulo

    (0 until ratio).map {
      n => hash + modulo * n
    }.toList
  }
}
