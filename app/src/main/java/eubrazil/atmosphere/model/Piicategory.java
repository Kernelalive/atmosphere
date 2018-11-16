package eubrazil.atmosphere.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "piicategory")
public class Piicategory implements Serializable, Comparable<Piicategory>  {

  private static final long serialVersionUID = 1L;

  @Id private Long id;

  @NotNull
  @Column(nullable = false, unique = true)
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
  public int compareTo(Piicategory o) {
    return this.name.compareTo(o.name);
  }
}
