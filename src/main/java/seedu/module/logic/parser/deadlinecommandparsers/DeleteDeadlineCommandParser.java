package seedu.module.logic.parser.deadlinecommandparsers;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TASK_LIST_NUMBER;

import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.deadlinecommands.DeleteDeadlineTaskCommand;
import seedu.module.logic.parser.ArgumentMultimap;
import seedu.module.logic.parser.ParserUtil;
import seedu.module.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDeadlineCommand object
 */
public class DeleteDeadlineCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDeadlineCommand
     * and returns a DeleteDeadlineCommand object for execution.
     * @param argsMultimap
     * @return DeleteDeadlineCommand
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteDeadlineTaskCommand parse(ArgumentMultimap argsMultimap) throws ParseException {
        Index index = ParserUtil.parseIndex(argsMultimap.getPreamble());
        if (argsMultimap.getValue(PREFIX_TASK_LIST_NUMBER).isPresent()) {
            try {
                int taskListNum = Integer.parseInt(argsMultimap.getValue(PREFIX_TASK_LIST_NUMBER).get().trim());
                return new DeleteDeadlineTaskCommand(index, taskListNum);
            } catch (NumberFormatException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDeadlineTaskCommand.MESSAGE_USAGE), e);
            }
        } else {
            throw new ParseException(DeleteDeadlineTaskCommand.MESSAGE_USAGE);
        }

    }
}
