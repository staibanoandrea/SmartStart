package it.andrea.smartstart;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvRequest {
	private Integer index;
	private Calendar requestTime = Calendar.getInstance(Locale.ITALY);
	private int x;
	private int y;
	private int depth;
	private int side;

	private final DateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.ITALY);
	private final Pattern laPattern = Pattern.compile("La(\\d+)");
	private final Pattern caPattern = Pattern.compile("Ca(\\d+)");
	private final Pattern liPattern = Pattern.compile("Li(\\d+)");
	private final Pattern coPattern = Pattern.compile("Co(\\d+)");
	private final Pattern prPattern = Pattern.compile("Pr(\\d+)");

	public CsvRequest(Integer index, String requestTime, String pos) throws ParseException {
		super();
		this.index = index;
		this.requestTime.setTime(sdf.parse(requestTime));

		Matcher la = laPattern.matcher(pos);
		Matcher ca = caPattern.matcher(pos);
		Matcher li = liPattern.matcher(pos);
		Matcher co = coPattern.matcher(pos);
		Matcher pr = prPattern.matcher(pos);

		this.x = Integer.parseInt(co.group(1));
		if (Integer.parseInt(ca.group(1)) > 1) {
			this.x += (Integer.parseInt(ca.group(1))) * 3 + 2;
		}

		this.y = Integer.parseInt(li.group(1));
		this.depth = Integer.parseInt(pr.group(1));
		this.side = Integer.parseInt(la.group(1));
	}

	public Integer getIndex() {
		return index;
	}

	public Calendar getRequestTime() {
		return requestTime;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getDepth() {
		return depth;
	}

	public int getSide() {
		return side;
	}

	public int getRTinSeconds() {
		Calendar startOfDay = Calendar.getInstance(Locale.ITALY);
		startOfDay.set(Calendar.HOUR_OF_DAY, 6);
		startOfDay.set(Calendar.MINUTE, 0);
		startOfDay.set(Calendar.SECOND, 0);
		startOfDay.set(Calendar.MILLISECOND, 0);
		requestTime.set(Calendar.MILLISECOND, 0);
		int seconds = ((int) (requestTime.getTimeInMillis() - startOfDay.getTimeInMillis())) / 1000;
		return seconds;
	}
}