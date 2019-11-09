package seedu.module.model.module.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.module.model.module.ArchivedModule;
import seedu.module.testutil.ArchivedModuleBuilder;

public class SameModuleCodePredicateTest {

    ArchivedModule archivedModule = new ArchivedModuleBuilder().build();


    @Test
    public void test_SameModuleCode_returnsTrue() {
        SameModuleCodePredicate sameModuleCodePredicate = new SameModuleCodePredicate("CS2103T");
        assertTrue(sameModuleCodePredicate.test(archivedModule));
    }

    @Test
    public void test_SameModuleCodeLowerCase_returnsTrue() {
        SameModuleCodePredicate sameModuleCodePredicate = new SameModuleCodePredicate("cs2103t");
        assertTrue(sameModuleCodePredicate.test(archivedModule));
    }

    @Test
    public void test_differentModuleCode_returnsFalse() {
        ArrayList<String> list = new ArrayList<>() {
            {
                add("cs2101");
            }
        };
        SameModuleCodePredicate sameModuleCodePredicate = new SameModuleCodePredicate("CS2101");
        assertFalse(sameModuleCodePredicate.test(archivedModule));
    }

    @Test
    public void test_partialModuleCode_returnsFalse() {
        SameModuleCodePredicate sameModuleCodePredicate = new SameModuleCodePredicate("CS2103");
        assertFalse(sameModuleCodePredicate.test(archivedModule));
    }

}
