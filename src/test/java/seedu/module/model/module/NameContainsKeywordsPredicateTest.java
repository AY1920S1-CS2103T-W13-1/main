package seedu.module.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.module.model.module.predicate.NameContainsKeywordsPredicate;
import seedu.module.testutil.ArchivedModuleBuilder;

public class NameContainsKeywordsPredicateTest {

    ArchivedModule archivedModule = new ArchivedModuleBuilder().build();


    @Test
    public void test_SameModuleCode_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("CS2103T");
            }
        };
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate = new NameContainsKeywordsPredicate(list);
        assertTrue(nameContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_SameModuleCodeLowerCase_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("cs2103t");
            }
        };
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate = new NameContainsKeywordsPredicate(list);
        assertTrue(nameContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_differentModuleCode_returnsFalse() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("cs2101");
            }
        };
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate = new NameContainsKeywordsPredicate(list);
        assertFalse(nameContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_partialModuleCode_returnsFalse() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("CS2103");
            }
        };
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate = new NameContainsKeywordsPredicate(list);
        assertFalse(nameContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_emptyList_returnsFalse() {
        ArrayList<String> list = new ArrayList<>();
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate = new NameContainsKeywordsPredicate(list);
        assertFalse(nameContainsKeywordsPredicate.test(archivedModule));
    }

}
