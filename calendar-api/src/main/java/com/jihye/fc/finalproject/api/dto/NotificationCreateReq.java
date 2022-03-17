package com.jihye.fc.finalproject.api.dto;

import com.jihye.fc.finalproject.core.util.TimeUnit;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class NotificationCreateReq {
	
	private String title;
	private LocalDateTime notifyAt;
	private RepeatInfo repeatInfo;
	
	public List<LocalDateTime> getRepeatTime() {
		if (repeatInfo == null) {
			return Collections.singletonList(notifyAt);
		}
		
		return IntStream.range(0, repeatInfo.times)
				  .mapToObj(i -> {
					  long increment = (long) repeatInfo.interval.intervalValue * i;
					  System.out.println(increment);
					  switch (repeatInfo.interval.timeUnit){
						  case DAY:
							  return notifyAt.plusDays(increment);
						  case WEEK:
							  return notifyAt.plusWeeks(increment);
						  case MONTH:
							  return notifyAt.plusMonths(increment);
						  case YEAR :
							  return notifyAt.plusYears(increment);
						  default :
							  throw new RuntimeException("bad request. not match time unit");
					  }
				  }).collect(Collectors.toList());
	}
	
	@Data
	public static class RepeatInfo{
		private Interval interval;
		private int times;
	}
	
	@Data
	public static class Interval{
		private int intervalValue;
		private TimeUnit timeUnit;
	}
}
