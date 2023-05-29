import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.{Instant, LocalDate, LocalDateTime, ZoneId}

class FormatterTest extends AnyFlatSpec with Matchers {

  private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  "Formatter" should "return Today when the date is same as system date" in {
    val now = System.currentTimeMillis()
    Formatter.format(now, now) should be("Today")
  }

  it should "return formatted date when the date is not the same as system date" in {
    val now = System.currentTimeMillis()
    val past = Instant.now().minus(1, ChronoUnit.DAYS).toEpochMilli

    val expectedDate = LocalDateTime.from(Instant.ofEpochMilli(past).atZone(ZoneId.systemDefault())).format(formatter)

    Formatter.format(past, now) should be(expectedDate)
  }

  it should "handle the case where the date is in the future" in {
    val future = Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli
    val now = System.currentTimeMillis()

    val expectedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(future), ZoneId.systemDefault()).format(formatter)
    Formatter.format(future, now) should be(expectedDate)
  }

  it should "handle the edge case where the date is the start of the epoch" in {
    val startOfEpoch = 0L
    val now = System.currentTimeMillis()

    val expectedDate = "01/01/1970"
    Formatter.format(startOfEpoch, now) should be(expectedDate)
  }
}
