package seedu.module.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.module.model.module.TrackedModule;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TrackedModule trackedModule;

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label id;

    public ModuleCard(TrackedModule trackedModule, int displayedIndex) {
        super(FXML);
        this.trackedModule = trackedModule;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(trackedModule.getModuleCode());
        title.setText(trackedModule.getTitle());
        description.setText(trackedModule.getDescription());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return id.getText().equals(card.id.getText())
                && trackedModule.equals(card.trackedModule);
    }
}
