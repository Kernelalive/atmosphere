package eubrazil.atmosphere.model;

/**
 * Identification level for Personally Identifiable Information
 *
 * @author Nomikos Mouzourakis nmouzourakis [at] ubitech.eu, mouzmike [at] gmail.com
 */
public enum PiiIdentification {
  LINKED("Linked"),
  LINKABLE("Linkable");

  private final String description;

  private PiiIdentification(final String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
