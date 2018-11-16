package eubrazil.atmosphere.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "legalground")
public class Legalground implements Serializable, Comparable<Legalground> {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(name = "name", unique = true, nullable = false)
  @Size(min = 1, max = 255)
  private String name;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "legalgroundtypeid", nullable = false)
  private Legalgroundtype legalgroundtype;

  @Column(name = "comments", unique = false, nullable = true)
  @Size(min = 0, max = 1024)
  private String comments;

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

  public Legalgroundtype getLegalgroundtype() {
    return legalgroundtype;
  }

  public void setLegalgroundtype(Legalgroundtype legalgroundtype) {
    this.legalgroundtype = legalgroundtype;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public int compareTo(Legalground o) { // TODO Auto-generated method stub
    return this.name.compareTo(o.name);
  }
}
