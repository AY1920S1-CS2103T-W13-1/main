package seedu.module.model.module.predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.module.model.module.ArchivedModule;
import seedu.module.testutil.ArchivedModuleBuilder;

public class DescriptionContainsKeywordsPredicateTest {

    ArchivedModule archivedModule = new ArchivedModuleBuilder().build();

    @Test
    public void test_SameDescription_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("Lorem");
                add("Ipsum");
            }
        };
        DescriptionContainsKeywordsPredicate descriptionContainsKeywordsPredicate = new DescriptionContainsKeywordsPredicate(list);
        assertTrue(descriptionContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_SameDescriptionLowerCase_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("lorem");
                add("ipsum");
            }
        };
        DescriptionContainsKeywordsPredicate descriptionContainsKeywordsPredicate = new DescriptionContainsKeywordsPredicate(list);
        assertTrue(descriptionContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_differentDescription_returnsFalse() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("hello");
            }
        };
        DescriptionContainsKeywordsPredicate descriptionContainsKeywordsPredicate = new DescriptionContainsKeywordsPredicate(list);
        assertFalse(descriptionContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_partialDescription_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("Lorem");
            }
        };
        DescriptionContainsKeywordsPredicate descriptionContainsKeywordsPredicate = new DescriptionContainsKeywordsPredicate(list);
        assertTrue(descriptionContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_notAllMatches_returnsFalse() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("Lorem");
                add("invalid");
            }
        };
        DescriptionContainsKeywordsPredicate descriptionContainsKeywordsPredicate = new DescriptionContainsKeywordsPredicate(list);
        assertFalse(descriptionContainsKeywordsPredicate.test(archivedModule));
    }

    @Test
    public void test_fuzzySearchSpellingMistakes_returnsTrue() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("Loem");
            }
        };
        DescriptionContainsKeywordsPredicate descriptionContainsKeywordsPredicate = new DescriptionContainsKeywordsPredicate(list);
        assertTrue(descriptionContainsKeywordsPredicate.test(archivedModule));
    }

}
