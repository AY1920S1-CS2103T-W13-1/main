package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.commons.core.Messages.MESSAGE_INVALID_SEARCH_FIELD;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.module.logic.commands.FindCommand;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.module.Module;
import seedu.module.model.module.predicate.DescriptionContainsKeywordsPredicate;
import seedu.module.model.module.predicate.ModuleCodeContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    private final ArrayList<String> prefixes = new ArrayList<>() {
        {
            add("module\\");
            add("desc\\");
        }
    };

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        ArrayList<ArrayList<String>> listOfLists = parseList(nameKeywords);
        ArrayList<Predicate<Module>> listOfPredicates = parseListOfLists(listOfLists);

        return new FindCommand(listOfPredicates);
    }

    /**
     * Parses the given {@code ArrayList<String>} of argument and returns a {@code ArrayList<ArrayList<Strings>>}
     * representing the command prefix and associating keywords.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    ArrayList<ArrayList<String>> parseList(String[] arr) throws ParseException {
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<>();
        ArrayList<String> currentList = new ArrayList<>();

        int counter = 1;
        for (String word : arr) {
            if (prefixes.contains(word)) {
                listOfLists.add(currentList);
                currentList = new ArrayList<>();
                currentList.add(word);
            } else {
                currentList.add(word);
            }
            if (counter == arr.length) {
                listOfLists.add(currentList);
            }
            counter++;
        }

        listOfLists.remove(0);

        if (listOfLists.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        for (ArrayList<String> list : listOfLists) {
            if (list.size() <= 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_SEARCH_FIELD));
            }
        }

        return listOfLists;
    }

    /**
     * Parses the given {@code ArrayList<ArrayList<Strings>>} of argument and returns a
     * {@code ArrayList<Predicate<Module>>} representing the predicates for each filter.
     *
     * @throws ParseException if prefix is not defined in prefixes.
     */
    ArrayList<Predicate<Module>> parseListOfLists(ArrayList<ArrayList<String>> listOfLists) throws ParseException {
        assert !listOfLists.isEmpty() : "listOfList is empty.";

        ArrayList<Predicate<Module>> listOfPredicates = new ArrayList<>();

        for (ArrayList<String> list : listOfLists) {
            switch (list.get(0)) {
            case "module\\":
                list.remove(0);
                listOfPredicates.add(new ModuleCodeContainsKeywordsPredicate(list));
                break;
            case "desc\\":
                list.remove(0);
                listOfPredicates.add(new DescriptionContainsKeywordsPredicate(String.join(" ", list)));
                break;
            default:
                throw new ParseException("parseListOfList received unknown prefix.");
            }
        }

        return listOfPredicates;
    }

}
