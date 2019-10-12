package seedu.module.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.module.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s moduleCode matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<TrackedModule> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(TrackedModule trackedModule) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(trackedModule.getModuleCode(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
