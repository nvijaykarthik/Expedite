package in.expedite.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import in.expedite.entity.Configuration;
import in.expedite.entity.Departments;
import in.expedite.entity.User;

public class SpecificationUtils {

	private SpecificationUtils() {
	}

	public static Specification<Configuration> getConfigurationSpecs(String key, String value) {

		return new Specification<Configuration>() {
			@Override
			public Predicate toPredicate(Root<Configuration> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (!StringUtils.isEmpty(key)) {
					predicates.add(cb.like(cb.lower(root.get("key")), "%" + key.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(value)) {
					predicates.add(cb.like(cb.lower(root.get("value")), "%" + value.toLowerCase() + "%"));
				}
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};

	}

	public static Specification<User> getUserSearchSpecs(String userId, String firstName, String secondName,
			String email, String state) {

		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (!StringUtils.isEmpty(userId)) {
					predicates.add(cb.like(cb.lower(root.get("userId")), "%" + userId.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(firstName)) {
					predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(secondName)) {
					predicates.add(cb.like(cb.lower(root.get("secondName")), "%" + secondName.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(email)) {
					predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(state)) {
					predicates.add(cb.like(cb.lower(root.get("state")), "%" + state.toLowerCase() + "%"));
				}
				
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};

	}

	public static Specification<Departments> getDepartmentsSpecs(String departmentName, String manager) {

		return new Specification<Departments>(){

			@Override
			public Predicate toPredicate(Root<Departments> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (!StringUtils.isEmpty(departmentName)) {
					predicates.add(cb.like(cb.lower(root.get("departmentName")), "%" + departmentName.toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(manager)) {
					Join<Departments, User> mngr = root.join("manager");
					predicates.add(cb.like(cb.lower(mngr.get("userId")), "%" + manager.toLowerCase() + "%"));
				}
					return cb.and(predicates.toArray(new Predicate[] {}));
			}
			
		};
	}
	
}
