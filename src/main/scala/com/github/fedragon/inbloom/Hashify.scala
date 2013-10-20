package com.github.fedragon.inbloom

trait Hashifier[B <: BaseFilter] {
  self: B =>

    def hashify(value: String): List[Int]
}

trait DefaultHashifier[B <: BaseFilter] extends Hashifier[B] {
  self: B =>

    val Ratio: Int = 4

    def hashify(value: String): List[Int] = {
      val modulo = self.bits.size / Ratio
      val hash = value.hashCode % modulo

      (0 until Ratio).map {
        n => hash + modulo * n
      }.toList
    }
}