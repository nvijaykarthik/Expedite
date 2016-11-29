package in.expedite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class AccessCode {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@NotBlank(message="Module should not be Empty")
	@Pattern(regexp="^[a-zA-Z0-9_]*$",message="In Valid Role Code")
	@Column(nullable=false)
	private String module;
	@Column(nullable=false)
	private String menuLink;
	@Column(nullable=false,unique=true)
	private String accessCode;
	@Column(nullable=false)
	private String accessDesc;
	
	@Transient
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getAccessDesc() {
		return accessDesc;
	}

	public void setAccessDesc(String accessDesc) {
		this.accessDesc = accessDesc;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "AccessCode [id=" + id + ", module=" + module + ", menuLink=" + menuLink + ", accessCode=" + accessCode
				+ ", accessDesc=" + accessDesc + ", active=" + active + "]";
	}
	
}
