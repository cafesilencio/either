import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class EitherTest {

    @Test
    fun foldRightTest() {
        val e = Either.right<Any, String>("foobar")

        e.fold({
            assertTrue(false) //shouldn't get called
        }, {
            assertThat(it, `is`("foobar"))
        })
    }

    @Test
    fun foldLeftTest() {
        val e = Either.left<Throwable, String>(RuntimeException())

        e.fold({
            assertTrue(true)
        }, {
            assertTrue(false) //shouldn't get called
        })
    }

    @Test
    fun flatMapRightTest() {
        val e = Either.right<Throwable, String>("foobar").flatMap { Either.Right<Throwable, String>("wubba") }

        e.fold({
            assertTrue(false) //shouldn't get called
        }, {
            assertThat(it, `is`("wubba"))
        })
    }

    @Test
    fun flatMapLeftTest() {
        val e = Either.left<Throwable, String>(RuntimeException()).flatMap { Either.Right<Throwable, String>("wubba") }

        e.fold({
            assertTrue(true)
        }, {
            assertTrue(false) //shouldn't get called
        })
    }

    @Test
    fun mapRightTest() {
        val e = Either.right<Throwable, String>("foobar").map { it.toUpperCase() }

        e.fold({
            assertTrue(false) //shouldn't get called
        }, {
            assertThat(it, `is`("FOOBAR"))
        })
    }

    @Test
    fun mapLeftTest() {
        val e = Either.left<Throwable, String>(RuntimeException()).map { it.toUpperCase() }

        e.fold({
            assertTrue(true)
        }, {
            assertTrue(false) //shouldn't get called
        })
    }


}
