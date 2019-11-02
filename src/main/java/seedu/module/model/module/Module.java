package seedu.module.model.module;

import java.util.List;
import java.util.Optional;

/**
 * Represents a Module.
 */
public interface Module {

    String getModuleCode();

    String getTitle();

    String getDescription();

    Optional<String> getPrerequisite();

    Optional<String> getPreclusion();

    SemesterDetailList getSemesterDetails();

    List<Integer> getListOfOfferedSemesters();

    default boolean isTracked() {
        return this instanceof Trackable;
    }
}
