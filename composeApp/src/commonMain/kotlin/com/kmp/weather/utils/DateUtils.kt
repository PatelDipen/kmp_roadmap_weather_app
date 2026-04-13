package com.kmp.weather.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

object DateUtils {

    private val monthShortNames = mapOf(
        Month.JANUARY to "Jan",
        Month.FEBRUARY to "Feb",
        Month.MARCH to "Mar",
        Month.APRIL to "Apr",
        Month.MAY to "May",
        Month.JUNE to "Jun",
        Month.JULY to "Jul",
        Month.AUGUST to "Aug",
        Month.SEPTEMBER to "Sep",
        Month.OCTOBER to "Oct",
        Month.NOVEMBER to "Nov",
        Month.DECEMBER to "Dec"
    )

    private val monthFullNames = mapOf(
        Month.JANUARY to "January",
        Month.FEBRUARY to "February",
        Month.MARCH to "March",
        Month.APRIL to "April",
        Month.MAY to "May",
        Month.JUNE to "June",
        Month.JULY to "July",
        Month.AUGUST to "August",
        Month.SEPTEMBER to "September",
        Month.OCTOBER to "October",
        Month.NOVEMBER to "November",
        Month.DECEMBER to "December"
    )

    /**
     * Converts date from yyyy-MM-dd format to dd-MMM format (e.g., "2026-04-13" to "13-Apr")
     * @param dateString Date string in yyyy-MM-dd format
     * @return Formatted date string in dd-MMM format
     */
    fun formatDateToDayMonth(dateString: String): String {
        return try {
            val date = LocalDate.parse(dateString)
            val monthShort = monthShortNames[date.month] ?: return dateString
            @Suppress("DEPRECATION")
            val day = date.dayOfMonth
            "$day-$monthShort"
        } catch (_: Exception) {
            dateString // Return original string if parsing fails
        }
    }

    /**
     * Converts date from yyyy-MM-dd format to full format (e.g., "2026-04-13" to "13-April")
     * @param dateString Date string in yyyy-MM-dd format
     * @return Formatted date string in dd-MMMM format
     */
    fun formatDateToFullMonth(dateString: String): String {
        return try {
            val date = LocalDate.parse(dateString)
            val monthFull = monthFullNames[date.month] ?: return dateString
            @Suppress("DEPRECATION")
            val day = date.dayOfMonth
            "$day-$monthFull"
        } catch (_: Exception) {
            dateString // Return original string if parsing fails
        }
    }
}

