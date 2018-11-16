package eubrazil.atmosphere.model;

import eubrazil.atmosphere.service.transfer.Graphable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "datasubject")
public class Datasubject extends Graphable implements Serializable, Comparable<Datasubject> {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(nullable = false, unique = true)
  @Size(min = 1, max = 64)
  private String name;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "datasubjectpii",
      joinColumns = @JoinColumn(name = "datasubjectid", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "piiid", referencedColumnName = "id")
  )
  private List<Pii> piis = new ArrayList<Pii>();

  public Datasubject() {}

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

  public List<Pii> getPiis() {
    return piis;
  }

  public void setPiis(List<Pii> piis) {
    this.piis = piis;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof Datasubject)) return false;
    Datasubject other = (Datasubject) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    return true;
  }

  @Override
  public int compareTo(Datasubject o) {
    return this.name.compareTo(o.name);
  }

}
