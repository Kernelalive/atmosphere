package eubrazil.atmosphere.rest.transfer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ProcessingActivityDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private String description;
  private Set<Long> processors = new HashSet<>();
  private Set<Long> controllers = new HashSet<>();
  private Set<Long> recipients = new HashSet<>();
  private Set<Long> legalgrounds = new HashSet<>();
  private String legalgroundtype;
  private Set<Long> processingtypes = new HashSet<>();
  private String processingtypecategory;
  private Set<Long> datasubjects = new HashSet<>();
  private Set<Long> piis = new HashSet<>();
  private Set<String> processingcountries = new HashSet<>();
  private Set<InformationItemDTO> informationitems = new HashSet<>();

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

  public Set<Long> getProcessors() {
    return processors;
  }

  public void setProcessors(Set<Long> processors) {
    this.processors = processors;
  }

  public Set<Long> getControllers() {
    return controllers;
  }

  public void setControllers(Set<Long> controllers) {
    this.controllers = controllers;
  }

  public Set<Long> getRecipients() {
    return recipients;
  }

  public void setRecipients(Set<Long> recipients) {
    this.recipients = recipients;
  }

  public Set<Long> getLegalgrounds() {
    return legalgrounds;
  }

  public void setLegalgrounds(Set<Long> legalgrounds) {
    this.legalgrounds = legalgrounds;
  }

  public String getLegalgroundtype() {
    return legalgroundtype;
  }

  public void setLegalgroundtype(String legalgroundtype) {
    this.legalgroundtype = legalgroundtype;
  }

  public String getProcessingtypecategory() {
    return processingtypecategory;
  }

  public void setProcessingtypecategory(String processingtypecategory) {
    this.processingtypecategory = processingtypecategory;
  }

  public Set<Long> getProcessingtypes() {
    return processingtypes;
  }

  public void setProcessingtypes(Set<Long> processingtypes) {
    this.processingtypes = processingtypes;
  }

  public Set<String> getProcessingcountries() {
    return processingcountries;
  }

  public void setProcessingcountries(Set<String> processingcountries) {
    this.processingcountries = processingcountries;
  }

  public Set<Long> getDatasubjects() {
    return datasubjects;
  }

  public void setDatasubjects(Set<Long> datasubjects) {
    this.datasubjects = datasubjects;
  }

  public Set<Long> getPiis() {
    return piis;
  }

  public void setPiis(Set<Long> piis) {
    this.piis = piis;
  }

  public Set<InformationItemDTO> getInformationitems() {
    return informationitems;
  }

  public void setInformationitems(Set<InformationItemDTO> informationitems) {
    this.informationitems = informationitems;
  }

  public static final InformationItemDTO createInformationItemDTO(
      final long datasubject, final long pii) {
    InformationItemDTO dto = new InformationItemDTO();
    dto.setDatasubject(datasubject);
    dto.setPii(pii);
    return dto;
  }

  public static class InformationItemDTO {
    private Long datasubject;
    private Long pii;

    public InformationItemDTO() {}

    public Long getDatasubject() {
      return datasubject;
    }

    public void setDatasubject(Long datasubject) {
      this.datasubject = datasubject;
    }

    public Long getPii() {
      return pii;
    }

    public void setPii(Long pii) {
      this.pii = pii;
    }

    @Override
    public String toString() {
      return "InformationItemDTO [datasubject=" + datasubject + ", pii=" + pii + "]";
    }
  }

  @Override
  public String toString() {
    return "ProcessingActivityDTO [id="
        + id
        + ", name="
        + name
        + ", description="
        + description
        + ", processors="
        + processors
        + ", controllers="
        + controllers
        + ", recipients="
        + recipients
        + ", legalgrounds="
        + legalgrounds
        + ", processingtypes="
        + processingtypes
        + ", datasubjects="
        + datasubjects
        + ", piis="
        + piis
        + ", processingcountries="
        + processingcountries
        + ", informationitems="
        + informationitems
        + "]";
  }

}
