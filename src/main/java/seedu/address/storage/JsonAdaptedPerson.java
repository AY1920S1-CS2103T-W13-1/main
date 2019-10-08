package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String moduleCode;
    private final String title;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("moduleCode") String moduleCode, @JsonProperty("title") String title,
                             @JsonProperty("description") String description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Module source) {
        moduleCode = source.getModuleCode();
        title = source.getTitle();
        description = source.getDescription();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Model} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "moduleCode"));
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "title"));
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }
        return new Module(moduleCode, title, description);
    }

}
