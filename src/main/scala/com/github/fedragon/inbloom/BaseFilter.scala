package com.github.fedragon.inbloom

abstract class BaseFilter (val bits: Vector[Int]) extends Hashifier[BaseFilter] with DefaultHashifier[BaseFilter] {

  require(bits.size > 0)

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
}
