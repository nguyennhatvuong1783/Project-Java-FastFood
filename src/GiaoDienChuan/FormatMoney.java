package GiaoDienChuan;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatMoney {	
	private static Locale locale = new Locale("en", "EN");
	private static String pattern = "###,###.# VNĐ";
    private static DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
    
    public static String getFormat(long money) {
        dcf.applyPattern(pattern);
		String format = (String) dcf.format(money);
		return format;
	}
}
