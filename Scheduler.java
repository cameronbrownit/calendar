import java.io.Console;

public class Scheduler {
	public static void main(String[] args) {
		Console console = System.console();
		if (console == null) {
			System.out.println("No console, exiting");
			System.exit(0);
		}
		
		Calendar calendar = new Calendar();
		
		System.out.println("Calendar Program.");
		System.out.println("Type 'help' for instructions");
		
		while(true) {
			System.out.print("$> ");
			String command = console.readLine();
			if(command.equals("exit")){
				System.exit(0);
			}else if(command.equals("help")){
				System.out.println(
				"USAGE:" +
				"There are four commands: list, add, remove, intersection\n\n" +
				"list\n" +
				"no arguments\n\n" +
				"add <start> <end> <repeat day> <meeting name>\n" + 
				"start and end must be dates in the format yyyy/mm/dd-hh:mm\n" +
				"repeat day must be either a day of the week e.g. Sunday or none if it is not a repeating meeting\n" +
				"meeting name can be a string (no spaces)\n\n" +
				"remove <meeting id>\n" + 
				"meeting id is the number showing on the left when listing meetings\n\n" +
				"intersetion <meeting id> <meeting id>\n" +
				"meeting id is the number showing on the left when listing meetings. Select two meetings"
				);
			}else{
				calendar.doCommand(command);
			}
		}
    }
}