package in.expedite.entity;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	@NotBlank(message="Role Code should not be Empty")
	@Pattern(regexp="^[A-Z0-9_]*$",message="In Valid Role Code")
	private String roleCode;

	@Column(nullable=false)
	@NotBlank(message="Role Description should not be Empty")
	private String roleDescription;
	
	@Column(nullable=false)
	private String status=State.ACTIVE.toString();

	@OneToMany(mappedBy="role")
	Set<RoleAccessXref> roleAccessXref=new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleCode=" + roleCode + ", roleDescription=" + roleDescription + ", status="
				+ status + "]";
	}
	
	
}