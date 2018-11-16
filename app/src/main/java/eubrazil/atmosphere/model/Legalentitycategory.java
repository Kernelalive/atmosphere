package eubrazil.atmosphere.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "legalentitycategory")
public class Legalentitycategory implements Serializable, Comparable<Legalentitycategory> {

  private static final long serialVersionUID = 1L;

  @Id private Long id;

  @NotNull
  @Size(min = 1)
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int compareTo(Legalentitycategory o) {
    return this.name.compareTo(o.name);
  }
}
