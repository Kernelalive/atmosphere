package eubrazil.atmosphere.model;

import eubrazil.atmosphere.service.transfer.Graphable;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pii")
public class Pii extends Graphable implements Serializable, Comparable<Pii> {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private PiiType type;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "identification", nullable = false)
  private PiiIdentification identification;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "categoryid")
  private Piicategory category;

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

  public PiiType getType() {
    return type;
  }

  public void setType(PiiType type) {
    this.type = type;
  }

  public PiiIdentification getIdentification() {
    return identification;
  }

  public void setIdentification(PiiIdentification identification) {
    this.identification = identification;
  }

  public Piicategory getCategory() {
    return category;
  }

  public void setCategory(Piicategory category) {
    this.category = category;
  }

  @Override
  public int compareTo(Pii o) {
    return this.name.compareTo(o.name);
  }

}
