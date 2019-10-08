package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ModuleBook} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[]{
                new Module("CS2030", "Introduction to Java", "much java"),
                new Module("CS2103T", "SOFTENG", "much cancer")
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Module sampleModule : getSampleModules()) {
            sampleAb.addPerson(sampleModule);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
