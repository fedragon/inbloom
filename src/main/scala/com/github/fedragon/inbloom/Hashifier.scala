package com.github.fedragon.inbloom

trait Hashifier {
  def bits: Vector[Int]
  def hashify(value: String): List[Int]
}

trait DefaultHashifier extends Hashifier {
  val Ratio: Int = 4

  override def hashify(value: String): List[Int] = {
    val modulo = bits.size / Ratio
    val hash = value.hashCode % modulo

    (0 until Ratio).map {
      n => hash + modulo * n
    }.toList
  }
}