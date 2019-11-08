package seedu.module.model.module;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.validator.routines.UrlValidator;

import seedu.module.model.module.exceptions.LinkAccessException;
import seedu.module.model.module.exceptions.LinkCreationException;

/**
 * Represents a Link in a TrackedModule
 */
public class Link {
    public static final String MESSAGE_CONSTRAINTS = "Not a valid URL";

    public final String url;
    public final String name;
    private boolean marked = false;

    public Link(String name, String url) throws LinkCreationException {

        requireNonNull(url);
        String properLink = url;
        if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://")) {
            properLink = "http://" + url;
        }
        if (!isValidUrl(properLink)) {
            throw new LinkCreationException(MESSAGE_CONSTRAINTS);
        }
        this.name = name;
        this.url = properLink;
    }

    public Link(String name, String url, boolean marked) throws LinkCreationException {

        requireNonNull(url);
        String properLink = url;
        if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://")) {
            properLink = "http://" + url;
        }
        if (!isValidUrl(properLink)) {
            throw new LinkCreationException(MESSAGE_CONSTRAINTS);
        }
        this.name = name;
        this.url = properLink;
        this.marked = marked;
    }

    /**
     * Returns True if the string given is a valid URL
     * @param url
     * @return
     */
    public static boolean isValidUrl(String url) {
        String[] scheme = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(scheme);
        return urlValidator.isValid(url);
    }

    /**
     * Launches the url in this Link in the user's default browser
     */
    public void launch() {
        String runningSystem = System.getProperty("os.name").toLowerCase();

        try {
            if (Desktop.isDesktopSupported()) { // Probably Windows
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(URI.create(url));
            } else { // Definitely Non-windows
                Runtime runtime = Runtime.getRuntime();
                if (runningSystem.contains("mac")) { // Apples
                    runtime.exec("open " + url);
                } else if (runningSystem.contains("nix") || runningSystem.contains("nux")) { // Linux flavours
                    runtime.exec("xdg-open " + url);
                } else {
                    throw new LinkAccessException("Unable/unwilling to launch a browser in your OS.");
                }
            }
        } catch (IOException e) {
            throw new LinkAccessException("Couldn't open system browser: " + e.getMessage());
        }
    }

    public boolean isMarked() {
        return this.marked;
    }

    public boolean setMarked() {
        if (this.marked) {
            return false;
        } else {
            this.marked = true;
            return true;
        }
    }

    public boolean setUnmarked() {
        if (!this.marked) {
            return false;
        } else {
            this.marked = false;
            return true;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && name.equals(((Link) other).name))
                && url.equals(((Link) other).url); // state check
    }

}
