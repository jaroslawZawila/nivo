import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDate, ZoneId}

object Formatter {

  private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  def format(dateToFormatTimeMillis: Long, systemDateTimeMillis: Long): String = {
    val dateTime= toDateTime(dateToFormatTimeMillis)
    val systemDateTime = toDateTime(systemDateTimeMillis)

    if (dateTime.isEqual(systemDateTime)) {
      "Today"
    } else {
      formatter.format(dateTime)
    }
  }

  private def toDateTime(time: Long): LocalDate = {
    Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDate()
  }

}
