package xyz.olympiccode.plugins.API.Funções;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UtilTime
{
  public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
  
  public static String now()
  {
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return localSimpleDateFormat.format(localCalendar.getTime());
  }
  
  public static String when(long paramLong)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return localSimpleDateFormat.format(Long.valueOf(paramLong));
  }
  
  public static String date()
  {
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return localSimpleDateFormat.format(localCalendar.getTime());
  }
  
  public static enum TimeUnit
  {
    FIT,  DAYS,  HOURS,  MINUTES,  SECONDS,  MILLISECONDS;
  }
  
  public static String since(long paramLong)
  {
    return "Took " + convertString(System.currentTimeMillis() - paramLong, 1, TimeUnit.FIT) + ".";
  }
  
  public static double convert(long paramLong, int paramInt, TimeUnit paramTimeUnit)
  {
    if (paramTimeUnit == TimeUnit.FIT) {
      if (paramLong < 60000L) {
        paramTimeUnit = TimeUnit.SECONDS;
      } else if (paramLong < 3600000L) {
        paramTimeUnit = TimeUnit.MINUTES;
      } else if (paramLong < 86400000L) {
        paramTimeUnit = TimeUnit.HOURS;
      } else {
        paramTimeUnit = TimeUnit.DAYS;
      }
    }
    if (paramTimeUnit == TimeUnit.DAYS) {
      return UtilMath.trim(paramInt, paramLong / 8.64E7D);
    }
    if (paramTimeUnit == TimeUnit.HOURS) {
      return UtilMath.trim(paramInt, paramLong / 3600000.0D);
    }
    if (paramTimeUnit == TimeUnit.MINUTES) {
      return UtilMath.trim(paramInt, paramLong / 60000.0D);
    }
    if (paramTimeUnit == TimeUnit.SECONDS) {
      return UtilMath.trim(paramInt, paramLong / 1000.0D);
    }
    return UtilMath.trim(paramInt, paramLong);
  }
  
  public static String MakeStr(long paramLong)
  {
    return convertString(paramLong, 1, TimeUnit.FIT);
  }
  
  public static String MakeStr(long paramLong, int paramInt)
  {
    return convertString(paramLong, paramInt, TimeUnit.FIT);
  }
  
  public static String convertString(long paramLong, int paramInt, TimeUnit paramTimeUnit)
  {
    if (paramLong == -1L) {
      return "Permanent";
    }
    if (paramTimeUnit == TimeUnit.FIT) {
      if (paramLong < 60000L) {
        paramTimeUnit = TimeUnit.SECONDS;
      } else if (paramLong < 3600000L) {
        paramTimeUnit = TimeUnit.MINUTES;
      } else if (paramLong < 86400000L) {
        paramTimeUnit = TimeUnit.HOURS;
      } else {
        paramTimeUnit = TimeUnit.DAYS;
      }
    }
    if (paramTimeUnit == TimeUnit.DAYS) {
      return UtilMath.trim(paramInt, paramLong / 8.64E7D) + " Days";
    }
    if (paramTimeUnit == TimeUnit.HOURS) {
      return UtilMath.trim(paramInt, paramLong / 3600000.0D) + " Hours";
    }
    if (paramTimeUnit == TimeUnit.MINUTES) {
      return UtilMath.trim(paramInt, paramLong / 60000.0D) + " Minutes";
    }
    if (paramTimeUnit == TimeUnit.SECONDS) {
      return UtilMath.trim(paramInt, paramLong / 1000.0D) + " Seconds";
    }
    return UtilMath.trim(paramInt, paramLong) + " Milliseconds";
  }
  
  public static boolean elapsed(long paramLong1, long paramLong2)
  {
    return System.currentTimeMillis() - paramLong1 > paramLong2;
  }
}
