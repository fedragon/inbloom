package com.github.fedragon.inbloom

case class BloomFilter(bits: Vector[Int] = (0 until 200).map(_ => 0).toVector) {
  
  def add(value: String): BloomFilter = {
    val hashes = toHashes(value)

    def update(updates: List[Int], result: Vector[Int]): Vector[Int] = {
      updates match {
        case Nil => result
        case head :: tail => update(tail, result.updated(head, 1))
      }
    }

    BloomFilter(update(hashes, bits))
  }

  def query(value: String) = {
    toHashes(value).map(hash => bits(hash)).forall(b => b == 1)
  }

  private[inbloom] def toHashes(value: String): List[Int] = {
    val ratio = 4
    val modulo = bits.size / ratio
    val hash = value.hashCode % modulo

    (0 until ratio).map {
      n => hash + modulo * n
    }.toList
  }
}
