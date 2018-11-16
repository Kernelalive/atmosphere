package eubrazil.atmosphere.model;

import eubrazil.atmosphere.service.transfer.Graphable;
import eubrazil.atmosphere.util.Country;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "processingactivity")
public class Processingactivity extends Graphable implements Serializable, Comparable<Processingactivity> {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(name = "name", nullable = false, unique = true)
  @Size(min = 1, max = 255)
  private String name;

  @Column(name = "description", nullable = true)
  @Size(min = 0, max = 1024)
  private String description;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @NotNull
  @JoinColumn(name = "controllerid")
  private Legalentity controller;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "processingactivityprocessor",
      joinColumns = @JoinColumn(name = "processingactivityid", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "processorid", referencedColumnName = "id")
  )
  @OrderBy(value = "name")
  private SortedSet<Legalentity> processors = new TreeSet<Legalentity>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "processingactivityrecipient",
    joinColumns = @JoinColumn(name = "processingactivityid", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "recipientid", referencedColumnName = "id")
  )
  @OrderBy(value = "name")
  private SortedSet<Legalentity> recipients = new TreeSet<Legalentity>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "processingactivityprocessingtype",
    joinColumns = @JoinColumn(name = "processingactivityid", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "processingtypeid", referencedColumnName = "id")
  )
  @OrderBy(value = "name")
  private SortedSet<Processingtypecategory> processingtypes = new TreeSet<Processingtypecategory>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "processingactivitylegalground",
    joinColumns = @JoinColumn(name = "processingactivityid", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "legalgroundid", referencedColumnName = "id")
  )
  @OrderBy(value = "name")
  private SortedSet<Legalground> legalgrounds = new TreeSet<Legalground>();

  @ElementCollection
  @OrderBy(value = "country")
  @Enumerated(EnumType.STRING)
  @JoinTable(
    name = "processingactivitycountries",
    joinColumns = @JoinColumn(name = "processingactivityid", referencedColumnName = "id")
  )
  @Column(name = "country", nullable = false)
  private SortedSet<Country> processingcountries = new TreeSet<Country>();

  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {CascadeType.ALL}
  )
  @JoinTable(
    name = "processingactivityinformationitem",
    joinColumns = @JoinColumn(name = "processingactivityid", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "informationitemid", referencedColumnName = "id")
  )
  @OrderBy(value = "datasubject, pii")
  private SortedSet<Processingactivityinformationitem> informationitems =
      new TreeSet<Processingactivityinformationitem>();

  @Override
  public int compareTo(Processingactivity o) {
    return this.name.compareTo(o.name);
  }

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

  public Legalentity getController() {
    return controller;
  }

  public void setController(Legalentity controller) {
    this.controller = controller;
  }

  public SortedSet<Legalentity> getProcessors() {
    return processors;
  }

  public void setProcessors(SortedSet<Legalentity> processors) {
    this.processors = processors;
  }

  public SortedSet<Legalentity> getRecipients() {
    return recipients;
  }

  public void setRecipients(SortedSet<Legalentity> recipients) {
    this.recipients = recipients;
  }

  public SortedSet<Processingtypecategory> getProcessingtypes() {
    return processingtypes;
  }

  public void setProcessingtypes(SortedSet<Processingtypecategory> processingtypes) {
    this.processingtypes = processingtypes;
  }

  public SortedSet<Legalground> getLegalgrounds() {
    return legalgrounds;
  }

  public void setLegalgrounds(SortedSet<Legalground> legalgrounds) {
    this.legalgrounds = legalgrounds;
  }

  public SortedSet<Country> getProcessingcountries() {
    return processingcountries;
  }

  public void setProcessingcountries(SortedSet<Country> processingcountries) {
    this.processingcountries = processingcountries;
  }

  public SortedSet<Processingactivityinformationitem> getInformationitems() {
    return informationitems;
  }

  public void setInformationitems(SortedSet<Processingactivityinformationitem> informationitems) {
    this.informationitems = informationitems;
  }
}
