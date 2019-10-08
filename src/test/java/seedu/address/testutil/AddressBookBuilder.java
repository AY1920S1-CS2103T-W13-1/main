package seedu.address.testutil;

import seedu.address.model.ModuleBook;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ModuleBook addressBook;

    public AddressBookBuilder() {
        addressBook = new ModuleBook();
    }

    public AddressBookBuilder(ModuleBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Module module) {
        addressBook.addModule(module);
        return this;
    }

    public ModuleBook build() {
        return addressBook;
    }
}
