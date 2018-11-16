package eubrazil.atmosphere.model;

import eubrazil.atmosphere.service.transfer.Graphable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "processingtypecategory")
public class Processingtypecategory extends Graphable implements Comparable<Processingtypecategory> {

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(name = "name", nullable = false, unique = true)
  @Size(min = 1, max = 255)
  private String name;

  @Column(name = "description", nullable = true)
  @Size(min = 0, max = 1024)
  private String description;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public int compareTo(Processingtypecategory o) {
    return this.name.compareTo(o.name);
  }
}
