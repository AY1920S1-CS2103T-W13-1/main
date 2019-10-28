package seedu.module.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.module.commons.core.LogsCenter;
import seedu.module.model.module.Module;
import seedu.module.model.module.Trackable;

/**
 * Panel containing the active displayed module.
 */
public class ModuleViewPanel extends UiPart<Region> {
    private static final String FXML = "ModuleViewPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModuleListPanel.class);

    @FXML
    private Label moduleCode;
    @FXML
    private Label title;
    @FXML
    private Text description;
    @FXML
    private FlowPane links;
    @FXML
    private FlowPane deadline;


    public ModuleViewPanel(Module module) {
        super(FXML);
        moduleCode.setText(module.getModuleCode());
        title.setText(module.getTitle());
        description.setText(module.getDescription());
        if (module instanceof Trackable) {
            Trackable trackedModule = ((Trackable) module);
            trackedModule.getLink().stream().map(link -> new LinkButton(link))
                    .forEach(button -> links.getChildren().add(button));
            links.setHgap(10);
        }
    }
}
