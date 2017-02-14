package in.expedite.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import in.expedite.entity.Configuration;

public class SpecificationUtils {

	private SpecificationUtils(){}
	
	public static Specification<Configuration> getConfigurationSpecs(String key, String value){
		
		return new Specification<Configuration>() {
			@Override
			public Predicate toPredicate(Root<Configuration> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates= new ArrayList<>();
				
				if(!StringUtils.isEmpty(key)){
					predicates.add(cb.like(cb.lower(root.get("key")), "%"+key.toLowerCase()+"%"));
				}
				if(!StringUtils.isEmpty(value)){
					predicates.add(cb.like(cb.lower(root.get("value")), "%"+value.toLowerCase()+"%"));
				}
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};
		
	}
}
