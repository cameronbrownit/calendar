import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Calendar {
	public LinkedList<CalendarItem> CalendarItemList = new LinkedList<CalendarItem>();
	
	public Calendar (){
		
	}

	public void doCommand(String command){
		String[] args = command.split(" ");
		switch(args[0]){
			case "list": list();
				break;
			case "add": add(args);
				break;
			case "remove": remove(args);
				break;
			case "intersection": intersection(args);
				break;
			default:
				System.out.println("Unrecognised command.");
				break;
		}
	}
	
	public void list(){
		for(int i = 0; i < CalendarItemList.size(); i++) {
            System.out.println(Integer.toString(i) + "  " + CalendarItemList.get(i).toString());
        }
	}
	public void add(String[] args){
		if (args.length < 5){
			System.out.println("Please check arguments.");
			return;
		}
		Date start;
		Date end;
		String repeatDate = null;
		String meetingName = args[4];
		
		try{
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
			start = df.parse(args[1]);
			end = df.parse(args[2]);
		}catch (Exception e){
			System.out.println("Error parsing date: please ensure it is in the format yyyy/mm/dd-hh:mm");
			return;
		}
		String[] week = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "NONE"};
		for(String day : week){
			if(day.equals(args[3].toUpperCase())){
				repeatDate = args[3].toUpperCase();
			}
        }
		if(repeatDate == null){
			System.out.println("Error parsing repeat date: please ensure it is a day e.g. 'Sunday' or 'none' if it doesn't repeat");
			return;
		}
		
		CalendarItem item = new CalendarItem(start, end, repeatDate, meetingName);
		if(CalendarItemList.size() < 1){
			CalendarItemList.add(item);
		}else if(item.start.before(CalendarItemList.getFirst().start)){
			CalendarItemList.addFirst(item);
		}
		else{
			ListIterator<CalendarItem> listIterator = CalendarItemList.listIterator();
			boolean added = false;
			while (listIterator.hasNext()) {
				// TODO: START SHOULD USE GETTERS
				if(item.start.before(listIterator.next().start)){
					listIterator.add(item);
					added = true;
					break;
				}
			}
			if(!added){
				CalendarItemList.add(item);
			}
		}
	}
	public void remove(String[] args){
		if (args.length < 2){
			System.out.println("Please check arguments.");
			return;
		}
		if(Integer.parseInt(args[1]) <= (CalendarItemList.size() - 1)){
			CalendarItemList.remove(Integer.parseInt(args[1]));
		}
	}
	public void intersection(String[] args){
		if (args.length < 3){
			System.out.println("Please check arguments.");
			return;
		}
		int a = Integer.parseInt(args[1]);
		int b = Integer.parseInt(args[2]);
		if(a >= CalendarItemList.size() || b >= CalendarItemList.size()){
			System.out.println("Meeting doesn't exist");
			return;
		}
		CalendarItem itemA = CalendarItemList.get(a);
		CalendarItem itemB = CalendarItemList.get(b);
		if (itemA.start.before(itemB.end) && itemB.start.before(itemA.end)){
			Date newStart = itemB.start; 
			Date newEnd = itemB.end;
			if (itemA.start.before(itemB.start)){
				newStart = itemA.start;
			}
			if (itemA.end.after(itemB.end)){
				newEnd = itemA.end;
			}
			System.out.println("Meetings intersect. Combined schedule is: " + new SimpleDateFormat("yyy-MM-dd HH:mm").format(newStart) + " to " + new SimpleDateFormat("yyy-MM-dd HH:mm").format(newEnd));
		}else{
			System.out.println("Meetings do not intersect");
		}
	}
}