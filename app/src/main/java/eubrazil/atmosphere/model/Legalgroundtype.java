package eubrazil.atmosphere.model;

import eubrazil.atmosphere.service.transfer.Graphable;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "legalgroundtype")
public class Legalgroundtype extends Graphable implements Serializable, Comparable<Legalgroundtype> {

  private static final long serialVersionUID = 1L;

  @Id private Long id;

  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @NotNull
  @Column(name = "weight", nullable = false, unique = true)
  private Integer weight;

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
  public int compareTo(Legalgroundtype o) {
    return this.weight.compareTo(o.weight);
  }
}
