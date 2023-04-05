package seedu.duke.commands;

import seedu.duke.food.FoodList;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;


public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    private static final String SHOW_ALL_COMMANDS = "List of commands: 'add', 'list', 'remove', 'find',"
            + " 'update', 'exit'."
            + "\nFor more detailed information on usage of specific command, type: help --COMMAND";
    private static final String DEFAULT_HELP_MESSAGE = "Refer to our user guide for more in-depth details on"
            + " how to use Food Supply Tracker:"
            + "\nhttps://ay2223s2-cs2113-w13-3.github.io/tp/UserGuide.html\n";

    private static final String HELP_ADD = "Command 'add': This command adds a food product to the food supply tracker."
            + "\nFormat: add -n PRODUCT_NAME -e EXPIRY_DATE {-c category} {-q QUANTITY -u UNIT}"
            + "\nFormat for EXPIRY_DATE: DD/MM/YYYY"
            + "\nIdentifier within brackets are optional"
            + "\nList of valid categories as follows: fruit, meat, vegetable, dairy, grain, seafood, beverage, others"
            + "\nAny category provided that does not fall into the above-mentioned categories will be grouped under "
            + "others."
            + "\nQUANTITY must be specified when UNITS is used together";

    private static final String HELP_LIST = "Command 'list': This command lists all food products in the tracker."
            + "\nFood products will be listed according to the order they are added into the food list.";

    private static final String HELP_REMOVE = "Command 'remove': This command removes the food product from the"
            + " food supply tracker based on its index."
            + "\nFormat: remove INDEX";

    private static final String HELP_FIND = "Command 'find': This command finds the food product by its name."
            + "\nFormat: find PRODUCT_NAME"
            + "\nAppend the filter '-fresh' for listing unexpired food products and "
            + "'-expired' for listing expired food products."
            + "\nAppend the filter '-c' followed by CATEGORY to find by category.";

    private static final String HELP_UPDATE = "Command 'update': This command allows users to update the name, "
            + "expiry date, quantity and units based on the index in the food list."
            + "\n All attributes: -n for Name, -e for ExpiryDate, -c for Category, -q for Quantity and -u for Units"
            + "can be updated with this command.";

    private static final String HELP_EXIT = "Command 'exit': This command will save the food list in an external file"
            + "before exiting the program.";
    private static final String REPORT_INVALID_INPUT = "Opps! The command %s is not valid.";

    private final HashSet<String> filters = new LinkedHashSet<>();
    public HelpCommand(String userInput) {
        filters.addAll(List.of(userInput.replaceAll(" ", "").split("--")));
    }

    public HashSet<String> getFilters() {
        return filters;
    }

    @Override
    public CommandResult execute(FoodList foodList) {
        String printToUser = "";
        if (filters.size() <= 1) {
            printToUser = SHOW_ALL_COMMANDS + '\n' + DEFAULT_HELP_MESSAGE;
            return new CommandResult(printToUser);
        }

        for (String f : filters) {
            if (isHelpOrEmpty(f)) {
                continue;
            }
            printToUser = addNewLine(printToUser);
            switch (f) {
            case AddCommand.COMMAND_WORD:
                printToUser = printToUser.concat(HELP_ADD);
                break;
            case ListCommand.COMMAND_WORD:
                printToUser = printToUser.concat(HELP_LIST);
                break;
            case RemoveCommand.COMMAND_WORD:
                printToUser = printToUser.concat(HELP_REMOVE);
                break;
            case FindCommand.COMMAND_WORD:
                printToUser = printToUser.concat(HELP_FIND);
                break;
            case UpdateCommand.COMMAND_WORD:
                printToUser = printToUser.concat(HELP_UPDATE);
                break;
            case ExitCommand.COMMAND_WORD:
                printToUser = printToUser.concat(HELP_EXIT);
                break;
            default:
                printToUser = printToUser.concat(String.format(REPORT_INVALID_INPUT, f));
                break;
            }
        }

        printToUser = addNewLine(printToUser);
        printToUser = printToUser.concat(SHOW_ALL_COMMANDS + '\n' + DEFAULT_HELP_MESSAGE);
        return new CommandResult(printToUser);
    }

    private boolean isHelpOrEmpty(String word) {
        return (word.equals(COMMAND_WORD) || word.equals(""));
    }

    private String addNewLine(String printToUser) {
        if (!printToUser.equals("")) {
            printToUser = printToUser.concat("\n\n");
        }
        return printToUser;
    }
}
