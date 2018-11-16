package eubrazil.atmosphere.model;

/**
 * Types for Personally Identifiable Information (PII) Special characterizes data like sexual
 * orientation, genetic info, health data and so on, while all the rest are considered normal.
 *
 * @author Nomikos Mouzourakis nmouzourakis [at] ubitech.eu, mouzmike [at] gmail.com
 */
public enum PiiType {
  N("Normal"),
  S("Sensitive");

  private final String description;

  private PiiType(final String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
