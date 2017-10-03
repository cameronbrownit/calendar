import java.util.Date;
import java.text.SimpleDateFormat;

public class CalendarItem {
	public Date start;
	public Date end;
	public String repeatDate;
	public String meetingName;
	
	public CalendarItem(Date _start, Date _end, String _repeatDate, String _meetingName){
		start = _start;
		end = _end;
		repeatDate = _repeatDate;
		meetingName = _meetingName;
	}
	
	@Override
    public String toString() {
		String startString = new SimpleDateFormat("yyy-MM-dd HH:mm").format(start);
		String endString = new SimpleDateFormat("yyy-MM-dd HH:mm").format(end);
		if(repeatDate.equals("NONE")){
			return "Meeting: " + meetingName + ", Starts: " + startString + ", Ends: " + endString;
		}else{
			return "Meeting: " + meetingName + ", Starts: " + startString + ", Ends: " + endString + ", Repeats " + repeatDate;
		}
    }
}