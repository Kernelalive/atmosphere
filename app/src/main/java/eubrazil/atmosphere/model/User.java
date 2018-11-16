package eubrazil.atmosphere.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(name = "fullname")
  private String fullName;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false, name = "datecreated")
  private Date dateCreated = new Date();

  @Column(nullable = false, name = "isenabled")
  private boolean isEnabled = false;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  public User() {
  }

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    this.isEnabled = enabled;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @JsonIgnore
  public String getRoleToString() {
    return role.name();
  }

  @JsonIgnore
  public List<String> getRolesList() {
    List<String> roles = new ArrayList<>();
    roles.add(this.role.name());
    return roles;
  }

}