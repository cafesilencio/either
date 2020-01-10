sealed class Either<E, out A> {

    companion object {
        fun <E, A> right(a: A): Right<E, A> = Right(a)
        fun <E, A> left(e: E): Left<E, A> = Left(e)
    }

    fun <B> map(f: (A) -> B): Either<E, B> = when(this) {
        is Right -> Right(f(value))
        is Left -> Left(value)
    }

    fun <B> flatMap(f: (A) -> Either<E,B>): Either<E, B> = when(this) {
        is Right -> f(value)
        is Left -> Left(value)
    }

    fun <C> fold(ifLeft: (E) -> C, ifRight: (A) -> C): C = when (this) {
        is Right -> ifRight(value)
        is Left -> ifLeft(value)
    }

    data class Left<E, A> (val value: E): Either<E,A>()
    data class Right<E, A> (val value: A): Either<E,A>()
}
fun <A> A.left(): Either<A, Nothing> = Either.Left(this)
fun <A> A.right(): Either<Nothing, A> = Either.Right(this)
