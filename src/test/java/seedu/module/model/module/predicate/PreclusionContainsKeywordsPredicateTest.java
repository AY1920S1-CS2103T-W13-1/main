package seedu.module.model.module.predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.module.model.module.ArchivedModule;
import seedu.module.testutil.ArchivedModuleBuilder;

public class PreclusionContainsKeywordsPredicateTest {

    ArchivedModule archivedModule = new ArchivedModuleBuilder().withPreclusions("CS2030").build();

    @Test
    public void test_SamePreclusionModuleCode_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("CS2030");
            }
        };
        PreclusionContainsKeywordsPredicate preclusionCodeContainsKeywordsPredicate = new PreclusionContainsKeywordsPredicate(list);
        assertTrue(preclusionCodeContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_SamePreclusionModuleCodeLowerCase_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("cs2030");
            }
        };
        PreclusionContainsKeywordsPredicate preclusionCodeContainsKeywordsPredicate = new PreclusionContainsKeywordsPredicate(list);
        assertTrue(preclusionCodeContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_differentPreclusionModuleCode_returnsFalse() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("CS2101");
            }
        };
        PreclusionContainsKeywordsPredicate preclusionCodeContainsKeywordsPredicate = new PreclusionContainsKeywordsPredicate(list);
        assertFalse(preclusionCodeContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_partialPreclusionModuleCode_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("CS2");
            }
        };
        PreclusionContainsKeywordsPredicate preclusionCodeContainsKeywordsPredicate = new PreclusionContainsKeywordsPredicate(list);
        assertTrue(preclusionCodeContainsKeywordsPredicate.test(archivedModule));
    }

}
