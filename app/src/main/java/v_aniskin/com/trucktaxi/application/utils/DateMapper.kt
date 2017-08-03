package v_aniskin.com.trucktaxi.application.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by root on 02.08.17.
 */
class DateMapper {

    companion object {

        val DATE_FORMAT: String = "dd-MM-yyyy";

        fun mapDate(date: Long): String {
            val dateFormat: DateFormat = SimpleDateFormat.getDateInstance()
            return dateFormat.format(Date(date*1000L))
        }
    }
}