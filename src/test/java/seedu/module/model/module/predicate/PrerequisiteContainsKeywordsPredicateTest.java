package seedu.module.model.module.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.module.model.module.ArchivedModule;
import seedu.module.testutil.ArchivedModuleBuilder;

public class PrerequisiteContainsKeywordsPredicateTest {

    ArchivedModule archivedModule = new ArchivedModuleBuilder().withPrerequisite("CS2030").build();

    @Test
    public void test_SamePrerequisiteModuleCode_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("CS2030");
            }
        };
        PrerequisiteContainsKeywordsPredicate prerequisiteCodeContainsKeywordsPredicate = new PrerequisiteContainsKeywordsPredicate(list);
        assertTrue(prerequisiteCodeContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_SamePrerequisiteModuleCodeLowerCase_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("cs2030");
            }
        };
        PrerequisiteContainsKeywordsPredicate prerequisiteCodeContainsKeywordsPredicate = new PrerequisiteContainsKeywordsPredicate(list);
        assertTrue(prerequisiteCodeContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_differentPrerequisiteModuleCode_returnsFalse() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("CS2101");
            }
        };
        PrerequisiteContainsKeywordsPredicate prerequisiteCodeContainsKeywordsPredicate = new PrerequisiteContainsKeywordsPredicate(list);
        assertFalse(prerequisiteCodeContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_partialPrerequisiteModuleCode_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("CS2");
            }
        };
        PrerequisiteContainsKeywordsPredicate prerequisiteCodeContainsKeywordsPredicate = new PrerequisiteContainsKeywordsPredicate(list);
        assertTrue(prerequisiteCodeContainsKeywordsPredicate.test(archivedModule));
    }

}
