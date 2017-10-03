Compile with something along the lines of:
> javac Scheduler.java Calendar.java CalendarItem.java

Run with something along the lines of:
> java Scheduler


Usage:

There are four commands: list, add, remove, intersection

list
no arguments

add <start> <end> <repeat day> <meeting name>
start and end must be dates in the format yyyy/mm/dd-hh:mm
repeat day must be either a day of the week e.g. Sunday or none if it is not a repeating meeting
meeting name can be a string (no spaces)

remove <meeting id>
meeting id is the number showing on the left when listing meetings

intersetion <meeting id> <meeting id>
meeting id is the number showing on the left when listing meetings. Select two meetings