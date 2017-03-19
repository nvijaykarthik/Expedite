package in.expedite.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Departments {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String departmentName;
	
	@ManyToOne
	private Departments parentDepartment;
	
    @OneToMany(mappedBy="parentDepartment")
    private Set<Departments> subDepartment;

    @OneToOne 
    private User manager;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Departments getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Departments parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public Set<Departments> getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Set<Departments> subDepartment) {
		this.subDepartment = subDepartment;
	}

	@Override
	public String toString() {
		return "Departments [id=" + id + ", departmentName=" + departmentName + ", parentDepartment=" + parentDepartment
				+ ", subDepartment=" + subDepartment + "]";
	}
}
