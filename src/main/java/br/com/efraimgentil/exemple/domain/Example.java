package br.com.efraimgentil.exemple.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="example")
@NamedQueries(value = {
		@NamedQuery( name = "EXEMPLE_FIND_ALL" , query = "SELECT e FROM Example e")
		
})
public class Example {
		
	public static final String FIND_ALL = "EXEMPLE_FIND_ALL";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name" , nullable = false )
	private String name;
	
	@Column(name = "description"  )
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date" , nullable = false )
	private Date date;
	
	public Example() {	}
	
	public Example(String name, String description, Date date) {
		super();
		this.name = name;
		this.description = description;
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Exemple [id=").append(id).append(", name=").append(name).append(", description=").append(description).append(", date=")
				.append(date).append("]");
		return builder.toString();
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}