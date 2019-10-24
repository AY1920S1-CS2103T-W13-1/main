package seedu.module.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.module.logic.commands.AddCommand;
import seedu.module.model.module.Module;
import seedu.module.model.module.SameModuleCodePredicate;

import java.util.function.Predicate;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddCommandParserTest {
    private static final String VALID_MODULE_CODE = "cs2103t";
    private AddCommandParser parser = new AddCommandParser();
    private SameModuleCodePredicate AddCommandParserPredicate = new SameModuleCodePredicate(VALID_MODULE_CODE);

    @Test
    public void parse_validArgs_returnsAddCommand() {
        assertParseSuccess(parser, VALID_MODULE_CODE, new AddCommand(AddCommandParserPredicate));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
