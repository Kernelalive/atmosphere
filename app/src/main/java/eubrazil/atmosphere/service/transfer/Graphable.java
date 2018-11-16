package eubrazil.atmosphere.service.transfer;

import java.io.Serializable;

public abstract class Graphable implements Serializable {

  private static final long serialVersionUID = 1L;

  public abstract String getName();

  public String getGraphKey() {
    return this.getClass().getSimpleName() + ":" + this.getName();
  }
}
