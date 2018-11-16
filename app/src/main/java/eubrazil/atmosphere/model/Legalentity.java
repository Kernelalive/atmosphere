package eubrazil.atmosphere.model;

import eubrazil.atmosphere.util.Country;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "legalentity")
public class Legalentity implements Serializable, Comparable<Legalentity> {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue private Long id;

  @NotNull
  @Size(min = 1)
  @Column(nullable = false, unique = true)
  private String name;

  @Size(min = 0)
  private String regno;

  @Size(min = 0)
  private String vat;

  @Size(min = 0)
  private String address;

  @Size(min = 0)
  private String zip;

  @Enumerated(EnumType.STRING)
  private Country country;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "categoryid")
  private Legalentitycategory category;

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

  public String getRegno() {
    return regno;
  }

  public void setRegno(String regno) {
    this.regno = regno;
  }

  public String getVat() {
    return vat;
  }

  public void setVat(String vat) {
    this.vat = vat;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public Legalentitycategory getCategory() {
    return category;
  }

  public void setCategory(Legalentitycategory category) {
    this.category = category;
  }

  @Override
  public int compareTo(Legalentity o) {
    return this.name.compareTo(o.name);
  }
}
