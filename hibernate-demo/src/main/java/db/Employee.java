package db;

import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Employee extends PanacheEntity{

	@Column(length=20,unique=true)
	public String name;

	@Column(length=15, unique=false)
	public String department;

	@Column(precision=0, unique=false)
	public Integer workExp;

	public Employee(){}

	public Employee(String name, String department, Integer workExp){
		this.name=name;
		this.department=department;
		this.workExp=workExp;
	}
}
