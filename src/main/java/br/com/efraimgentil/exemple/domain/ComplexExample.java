package br.com.efraimgentil.exemple.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ComplexExample implements Serializable {

  private static final long serialVersionUID = 5465294906502543218L;

  @Id
  @GeneratedValue
  private Integer id;
  
  private String name;
  
  @OneToMany(mappedBy = "complexExample" , cascade = CascadeType.ALL )
  private List<Example> examples = new ArrayList<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Example> getExamples() {
    return examples;
  }

  public void setExamples(List<Example> examples) {
    this.examples = examples;
  }
  
}