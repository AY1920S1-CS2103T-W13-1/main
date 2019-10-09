package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' module book file path.
     */
    Path getModuleBookFilePath();

    /**
     * Sets the user prefs' module book file path.
     */
    void setModuleBookFilePath(Path moduleBookFilePath);

    /**
     * Replaces module book data with the data in {@code moduleBook}.
     */
    void setModuleBook(ReadOnlyModuleBook moduleBook);

    /**
     * Returns the ModuleBook
     */
    ReadOnlyModuleBook getModuleBook();

    /**
     * Returns true if a Module with the same identity as {@code Module} exists in the module book.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in the module book.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in the module book.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the module book.
     * The module identity of {@code editedModule} must not be the same as another existing module in the module book.
     */
    void setModule(Module target, Module editedModule);

    /**
     * Returns an unmodifiable view of the filtered module list
     */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);
}
