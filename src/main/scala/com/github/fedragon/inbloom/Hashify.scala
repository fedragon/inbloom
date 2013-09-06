package com.github.fedragon.inbloom

class Hashify(val size: Int, val ratio: Int) {

  def apply(value: String): List[Int] = {
    val modulo = size / ratio
    val hash = value.hashCode % modulo

    (0 until ratio).map {
      n => hash + modulo * n
    }.toList
  }
}