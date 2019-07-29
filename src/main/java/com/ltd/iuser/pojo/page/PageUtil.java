package com.ltd.iuser.pojo.page;

import com.ltd.iuser.enums.Code;
import com.ltd.iuser.pojo.BusinessException;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.query.criteria.internal.path.SingularAttributePath;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class PageUtil {

	public static <T> Specification<T> getSpecification(PageQuery pageQuery) {
		return getSpecification(pageQuery.getFields());
	}

	private static <T> Specification<T> getSpecification(Set<PageQuery.Field> fields) {
		if (CollectionUtils.isEmpty(fields)) {
			return null;
		}
		return (root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			fields.stream().forEach(field -> {
				if (ObjectUtils.isEmpty(field.getValue())) {
					return;
				}
				List<Predicate> multiPredicate = new ArrayList<>();
				String[] nameArr = field.getName().split("\\.");
				Path<String> path = root.get(nameArr[0]);
				int len = nameArr.length;
				if (len > 1) {
					for (int i = 1; i < len; i++) {
						path = path.get(nameArr[i]);
					}
				}
				if (path.getJavaType().isEnum()) {
					field.setValue(Enum.valueOf(((SingularAttributePath) path).getJavaType(), String.valueOf(field.getValue())));
				}
				switch (field.getRule()) {
					case EQ:
						multiPredicate.add(criteriaBuilder.equal(path, field.getValue()));
						break;
					case GL:
						throw new BusinessException(Code.RULE_UNIMPLEMENTED, String.format("rule=%s", field.getRule()));
//						break;
					case GELE:
						throw new BusinessException(Code.RULE_UNIMPLEMENTED, String.format("rule=%s", field.getRule()));
//						break;
					case GLE:
						throw new BusinessException(Code.RULE_UNIMPLEMENTED, String.format("rule=%s", field.getRule()));
//						break;
					case GEL:
						throw new BusinessException(Code.RULE_UNIMPLEMENTED, String.format("rule=%s", field.getRule()));
//						break;
					case LT:
						throw new BusinessException(Code.RULE_UNIMPLEMENTED, String.format("rule=%s", field.getRule()));
//						break;
					case GT:
						throw new BusinessException(Code.RULE_UNIMPLEMENTED, String.format("rule=%s", field.getRule()));
//						break;
					case LK:
						multiPredicate.add(criteriaBuilder.like(path, "%" + field.getValue() + "%"));
						break;
					case LL:
						throw new BusinessException(Code.RULE_UNIMPLEMENTED, String.format("rule=%s", field.getRule()));
//						break;
					case RL:
						throw new BusinessException(Code.RULE_UNIMPLEMENTED, String.format("rule=%s", field.getRule()));
//						break;
					default:
						throw new BusinessException(Code.RULE_UNIMPLEMENTED, String.format("rule=%s", field.getRule()));
				}
				predicates.add(criteriaBuilder.or(multiPredicate.toArray(new Predicate[multiPredicate.size()])));
			});
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}


	public static Pageable getPageable(PageQuery pageQuery) {
		return getPageable(pageQuery.getNumber(), pageQuery.getSize(), pageQuery.getSorts());
	}

	public static Pageable getPageable(int number, int size, Set<PageQuery.Sort> sorts) {
		if (CollectionUtils.isEmpty(sorts)) {
			return new PageRequest(number-1, size);
		} else {
			return new PageRequest(number-1, size, getSort(sorts));
		}
	}

	public static Sort getSort(Set<PageQuery.Sort> sorts) {
		if (CollectionUtils.isEmpty(sorts)) {
			return null;
		}
		List<Order> orders = sorts.stream()
				.map(sort -> new Order(Sort.Direction.fromString(sort.getOrder().name()), sort.getProperty()))
				.collect(Collectors.toList());
		return new Sort(orders);
	}
}
