import java.util.Scanner;
import java.util.ArrayList;

public class Dash {
    public static void main(String[] args) {
        //Initialise scanner for user input
        Scanner scanner = new Scanner(System.in);

        //Initialise array list to store date
        ArrayList<Task> list = new ArrayList<>();

        //Greeting
        String logo = " .----------------.  .----------------.  .----------------.  .----------------.\n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |  ________    | || |      __      | || |    _______   | || |  ____  ____  | |\n" +
                "| | |_   ___ `.  | || |     /  \\     | || |   /  ___  |  | || | |_   ||   _| | |\n" +
                "| |   | |   `. \\ | || |    / /\\ \\    | || |  |  (__ \\_|  | || |   | |__| |   | |\n" +
                "| |   | |    | | | || |   / ____ \\   | || |   '.___`-.   | || |   |  __  |   | |\n" +
                "| |  _| |___.' / | || | _/ /    \\ \\_ | || |  |`\\____) |  | || |  _| |  | |_  | |\n" +
                "| | |________.'  | || ||____|  |____|| || |  |_______.'  | || | |____||____| | |\n" +
                "| |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'";
        String horizontalLine = "________________________________________";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");
        System.out.println(horizontalLine);

        //Main loop
        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {                              //list command
                    System.out.println(horizontalLine);
                    int length = list.size();
                    for (int i = 0; i < length; i++) {
                        Task t = list.get(i);
                        int num = i + 1;
                        System.out.println(num +"." + t);
                    }
                    System.out.println(horizontalLine);
                } else if (input.startsWith("mark")) {                          //mark command
                    String[] string = input.split(" ", 2);
                    int markNum = Integer.parseInt(string[1]);
                    int num = list.size();
                    if (markNum > num || markNum < 1) {
                        System.out.println(horizontalLine);
                        throw new WrongIndexException("Index is out of bounds.");
                    } else {
                        Task t = list.get(markNum - 1);
                        t.markTaskAsDone();
                        System.out.println(horizontalLine);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(t);
                        System.out.println(horizontalLine);
                    }
                } else if (input.startsWith("unmark")) {                       //unmark command
                    String[] string = input.split(" ", 2);
                    int unmarkNum = Integer.parseInt(string[1]);
                    int num = list.size();
                    if (unmarkNum > num || unmarkNum < 1) {
                        System.out.println(horizontalLine);
                        throw new WrongIndexException("Index is out of bounds.");
                    } else {
                        Task t = list.get(unmarkNum - 1);
                        t.unmarkTask();
                        System.out.println(horizontalLine);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(t);
                        System.out.println(horizontalLine);
                    }
                } else if (input.startsWith("todo")) {
                    System.out.println(horizontalLine);
                    String[] string = input.split(" ", 2);
                    if (string.length == 1) {
                        throw new EmptyDescriptionException("Description of todo command cannot be empty.");
                    }
                    Todo t = new Todo(string[1]);
                    list.add(t);
                    int num = list.size();
                    System.out.println("Got it. I've added this task:\n" + t);
                    System.out.println("Now you have " + num + " tasks in the list.");
                    System.out.println(horizontalLine);
                } else if (input.startsWith("deadline")) {
                    System.out.println(horizontalLine);
                    String[] string = input.split("/", 2); //"deadline XX" and "by XX"
                    if (string.length == 1) {
                        throw new EmptyDescriptionException("Description of deadline command cannot be empty. " +
                                "Do remember to include '/'.");
                    }
                    String[] byString = string[1].split(" ", 2); //"by" and "XX"
                    if (!byString[0].equals("by")) {
                        throw new IncorrectCommandUseException("Please include the /by command.");
                    }
                    if (byString.length == 1) {
                        throw new IncorrectCommandUseException("Please include deadline!");
                    }
                    String date = byString[1];
                    String[] string2 = string[0].split(" ", 2); //"deadline" and "XX"
                    String desc = string2[1];
                    Deadline t = new Deadline(desc, date);
                    list.add(t);
                    int num = list.size();
                    System.out.println("Got it. I've added this task:\n" + t);
                    System.out.println("Now you have " + num + " tasks in the list.");
                    System.out.println(horizontalLine);
                } else if (input.startsWith("event")) {
                    System.out.println(horizontalLine);
                    String[] string = input.split("/", 3); //"event XX" and "from XX" and "to XX"
                    if (string.length == 1) {
                        throw new EmptyDescriptionException("Description of todo command cannot be empty. " +
                                "Do remember to include '/'.");
                    } else if (string.length == 2) {
                        throw new IncorrectCommandUseException("Incorrect use of event command!");
                    }
                    String[] fromString = string[1].split(" ", 2); //"from" and "XX"
                    if (fromString.length == 1) {
                        throw new IncorrectCommandUseException("Incorrect use of event command!");
                    }
                    if (!fromString[0].equals("from")) {
                        throw new IncorrectCommandUseException("Please include the /from command.");
                    }
                    //TO ADD: EXCEPTION WHEN FROM AND TO IS NOT USED
                    String[] toString = string[2].split(" ", 2); //"to" and "XX"
                    if (toString.length == 1) {
                        throw new IncorrectCommandUseException("Incorrect use of event command!");
                    }
                    if (!toString[0].equals("to")) {
                        throw new IncorrectCommandUseException("Please include the /to command.");
                    }
                    String from = fromString[1];
                    String to = toString[1];
                    String[] eventString = string[0].split(" ", 2); //"event" and "XX"
                    String desc = eventString[1];
                    Event t = new Event(desc, from, to);
                    list.add(t);
                    int num = list.size();
                    System.out.println("Got it. I've added this task:\n" + t);
                    System.out.println("Now you have " + num + " tasks in the list.");
                    System.out.println(horizontalLine);
                } else {
                    throw new UnknownCommandException("Unknown command.");
                }
            } catch (UnknownCommandException | EmptyDescriptionException
                     | IncorrectCommandUseException | WrongIndexException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println(horizontalLine);
            } catch (Exception e) {
                System.out.println("UNEXPECTED ERROR: " + e.getMessage());
                System.out.println(horizontalLine);
            }
        }

        //Exit
        System.out.println(horizontalLine);
        String goodbye = "   ___                _ _                  _ \n" +
                "  / _ \\___   ___   __| | |__  _   _  ___  / \\\n" +
                " / /_\\/ _ \\ / _ \\ / _` | '_ \\| | | |/ _ \\/  /\n" +
                "/ /_\\\\ (_) | (_) | (_| | |_) | |_| |  __/\\_/ \n" +
                "\\____/\\___/ \\___/ \\__,_|_.__/ \\__, |\\___\\/   \n" +
                "                              |___/          ";
        System.out.println(goodbye);
        System.out.println(horizontalLine);
    }
}