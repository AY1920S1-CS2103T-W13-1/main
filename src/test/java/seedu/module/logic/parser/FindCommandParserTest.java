package seedu.module.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.module.logic.commands.FindCommand;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.module.Module;
import seedu.module.model.module.predicate.ModuleCodeContainsKeywordsPredicate;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the FindCommandParser code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class FindCommandParserTest {
    private static String VALID_STRING = "module\\ cs";
    private FindCommandParser parser = new FindCommandParser();
    private List<String> validModuleArguments = Arrays.asList("cs");
    private List<Predicate<Module>> validPredicateList =
            Arrays.asList(new ModuleCodeContainsKeywordsPredicate(validModuleArguments));


    @Test
    public void parseList_validArgs_returnsListOfLists() throws ParseException {
        String[] keywords = {"module\\", "cs"};
        ArrayList<ArrayList<String>> correctList = new ArrayList<>();
        ArrayList<String> correctModule = new ArrayList<>() {
            {
                add("module\\");
                add("cs");
            }
        };
        correctList.add(correctModule);
        assertEquals(correctList, parser.parseList(keywords));
    }

    @Test
    public void parseList_invalidArgsAndValidArgs_returnsListOfLists() throws ParseException {
        String[] keywords = {"INVALID TEXT", "INVALID TEXT 2", "module\\", "cs"};
        ArrayList<ArrayList<String>> correctList = new ArrayList<>();
        ArrayList<String> correctModule = new ArrayList<>() {
            {
                add("module\\");
                add("cs");
            }
        };
        correctList.add(correctModule);
        assertEquals(correctList, parser.parseList(keywords));
    }

    @Test
    public void parseList_validArgsWithNonsenseFields_returnsListOfLists() throws ParseException {
        String[] keywords = {"module\\", "mod\\"};
        ArrayList<ArrayList<String>> correctList = new ArrayList<>();
        ArrayList<String> correctModule = new ArrayList<>() {
            {
                add("module\\");
                add("mod\\");
            }
        };
        correctList.add(correctModule);
        assertEquals(correctList, parser.parseList(keywords));
    }

    @Test
    public void parseList_InvalidArgs_throwsParseException() {
        String[] keywords = {"INVALID INPUT"};
        assertThrows(ParseException.class, () -> parser.parseList(keywords));
    }

    @Test
    public void parseList_emptyFields_throwsParseException() {
        String[] keywords = {"module\\"};
        assertThrows(ParseException.class, () -> parser.parseList(keywords));
    }

    @Test
    public void parseListOfLists_validArgs_returnsListOfLists() {
        ArrayList<ArrayList<String>> inputList = new ArrayList<>();
        ArrayList<String> correctModule = new ArrayList<>() {
            {
                add("module\\");
                add("cs");
                add("ma");
            }
        };
        inputList.add(correctModule);

        ArrayList<ModuleCodeContainsKeywordsPredicate> correctList = new ArrayList<>();
        ArrayList<String> moduleArguments = new ArrayList<>() {
            {
                add("cs");
                add("ma");
            }
        };
        ModuleCodeContainsKeywordsPredicate correctModulePredicate = new ModuleCodeContainsKeywordsPredicate(moduleArguments);
        correctList.add(correctModulePredicate);

        assertEquals(correctList, parser.parseListOfLists(inputList));
    }

    @Test
    public void parseListOfLists_validArgsUpperCase_returnsListOfLists() {
        ArrayList<ArrayList<String>> inputList = new ArrayList<>();
        ArrayList<String> correctModule = new ArrayList<>() {
            {
                add("module\\");
                add("CS");
                add("MA");
            }
        };
        inputList.add(correctModule);

        ArrayList<ModuleCodeContainsKeywordsPredicate> correctList = new ArrayList<>();
        ArrayList<String> moduleArguments = new ArrayList<>() {
            {
                add("cs");
                add("ma");
            }
        };
        ModuleCodeContainsKeywordsPredicate correctModulePredicate = new ModuleCodeContainsKeywordsPredicate(moduleArguments);
        correctList.add(correctModulePredicate);

        assertEquals(correctList, parser.parseListOfLists(inputList));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        assertParseSuccess(parser, VALID_STRING, new FindCommand(validPredicateList));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
