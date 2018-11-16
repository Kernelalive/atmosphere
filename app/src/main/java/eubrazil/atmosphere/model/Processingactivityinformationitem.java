package eubrazil.atmosphere.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "informationitem")
public class Processingactivityinformationitem
    implements Comparable<Processingactivityinformationitem> {

  @Id @GeneratedValue private Long id;

  @NotNull
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "datasubjectid")
  private Datasubject datasubject;

  @NotNull
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "piiid")
  private Pii pii;

  public Processingactivityinformationitem() {}

  public Processingactivityinformationitem(final Datasubject datasubject, final Pii pii) {
    this.datasubject = datasubject;
    this.pii = pii;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Datasubject getDatasubject() {
    return datasubject;
  }

  public void setDatasubject(Datasubject datasubject) {
    this.datasubject = datasubject;
  }

  public Pii getPii() {
    return pii;
  }

  public void setPii(Pii pii) {
    this.pii = pii;
  }

  @Override
  public int compareTo(Processingactivityinformationitem o) {
    int res = this.datasubject.getName().compareTo(o.datasubject.getName());
    if (res != 0) {
      return res;
    }
    return this.pii.getName().compareTo(o.pii.getName());
  }
}
