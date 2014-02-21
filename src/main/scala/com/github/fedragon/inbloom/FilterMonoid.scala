package com.github.fedragon.inbloom

import scalaz.Monoid

// BloomFilter as a Set: exists, :+, ...

trait FilterMonoid extends Monoid[Vector[Int]] {
	val bits: Vector[Int]

	def zero: Vector[Int] = Vector(1)

	override def append(v1: Vector[Int], v2: => Vector[Int]): Vector[Int] = {
		if(v1.isEmpty) v2
		else {
			val other = v2

			if(other.isEmpty) v1
			else
				for {
					i <- v1
					j <- other
				} yield i * j
			}
	}
}
