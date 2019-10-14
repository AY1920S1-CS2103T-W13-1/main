package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;

import java.util.Arrays;

import seedu.module.commons.core.index.Index;
import seedu.module.commons.exceptions.IllegalValueException;
import seedu.module.logic.commands.AddCommand;
import seedu.module.logic.commands.AddDeadlineCommand;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.module.NameContainsKeywordsPredicate;

public class AddDeadlineCommandParser implements Parser<AddDeadlineCommand>  {

    public AddDeadlineCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeadlineCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeadlineCommand.MESSAGE_USAGE), ive);
        }
        String remark = argMultimap.getValue(PREFIX_DEADLINE).orElse("");

        return new AddDeadlineCommand(index, remark);
    }
}
