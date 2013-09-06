package com.github.fedragon.inbloom

case class BloomFilter(bits: Vector[Int] = (0 until 20).toVector) {
  
  val functions: Seq[String => Int] = Seq(
    (value: String) => value.hashCode % 5,
    (value: String) => (value.hashCode % 5) + 5,
    (value: String) => (value.hashCode % 5) + 10,
    (value: String) => (value.hashCode % 5) + 15
  )
}
