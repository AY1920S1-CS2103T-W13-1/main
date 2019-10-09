package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ModuleBook;
import seedu.address.model.ReadOnlyModuleBook;
import seedu.address.model.module.Module;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableModuleBook {

    public static final String MESSAGE_DUPLICATE_MODULES = "Module list contains duplicate Module(s).";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given modules.
     */
    @JsonCreator
    public JsonSerializableModuleBook(@JsonProperty("persons") List<JsonAdaptedModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableModuleBook(ReadOnlyModuleBook source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModuleBook toModelType() throws IllegalValueException {
        ModuleBook addressBook = new ModuleBook();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (addressBook.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULES);
            }
            addressBook.addModule(module);
        }
        return addressBook;
    }

}
