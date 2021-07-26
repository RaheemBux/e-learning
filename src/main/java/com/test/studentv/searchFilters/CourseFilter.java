package com.test.studentv.searchFilters;

import com.test.studentv.entity.CourseEntity;
import com.test.studentv.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseFilter implements Specification<CourseEntity> {

    private String name;

    @Override
    public Predicate toPredicate(Root<CourseEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (name != null && !name.equals("") && !name.equals("undefined")){
            predicates.add(builder.like(builder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
