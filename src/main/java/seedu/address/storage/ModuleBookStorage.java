package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ModuleBook;
import seedu.address.model.ReadOnlyModuleBook;

/**
 * Represents a storage for {@link ModuleBook}.
 */
public interface ModuleBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyModuleBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModuleBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyModuleBook> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyModuleBook} to the storage.
     *
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyModuleBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyModuleBook)
     */
    void saveAddressBook(ReadOnlyModuleBook addressBook, Path filePath) throws IOException;

}
