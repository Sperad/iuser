package com.ltd.iuser.pojo.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer number;

    private Integer size;

    private Set<Field> fields;

    private Set<Sort> sorts;

    @Getter
    @Setter
    public static class Field {

        public enum Rule {
            EQ, GL, GELE, GLE, GEL, LT, GT, LK, LL, RL
        }

        private String name;

        private Object value;

        private Object minValue;

        private Object maxValue;

        private Rule rule;

    }

    @Getter
    @Setter
    public static class Sort {

        public enum Order {
            ASC, DESC
        }
        private Order order;

        private String property;
    }
}
