package seedu.module.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.module.commons.util.StringUtil;

/**
 * Tests that a {@code ArchivedModule}'s moduleCode matches any of the keywords given.
 */
public class ArchivedNameContainsKeywordsPredicate implements Predicate<ArchivedModule> {
    private final List<String> keywords;

    public ArchivedNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ArchivedModule module) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getModuleCode(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchivedNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ArchivedNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
