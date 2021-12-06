package me.saksonov.scalaconsulting.generics.c_variance.с_contrvariance

object CovToInvExample {

  class Pair[+T](val first: T, val second: T) {
    // covariant type occurs in contravariant position:
    //def replaceFirst(newFirst: T) = new Pair[T](newFirst, second) // FIXME

    // invariant type is allowed in contravariant position:
    def replaceFirst[R >: T](newFirst: R) = new Pair[R](newFirst, second)

    // >: is "lower bound" - type R must be supertype of T
  }

}
