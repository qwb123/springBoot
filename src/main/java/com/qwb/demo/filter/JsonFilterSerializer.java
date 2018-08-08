package com.qwb.demo.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


/**
 * 通过ObjectMapper  过滤字段
 *
 * @author qwb
 */
@JsonFilter("JacksonFilter")
public class JsonFilterSerializer {

    private static final String FIELD_INCLUDE = "FIELD_INCLUDE";
    private static final String FIELD_EXCLUDE = "FIELD_EXCLUDE";
    private ObjectMapper objectMapper = new ObjectMapper();

    @JsonFilter(FIELD_INCLUDE)
    interface DynamicInclude {

    }

    @JsonFilter(FIELD_EXCLUDE)
    interface DynamicExclude {

    }

    //根据参数来创建过滤字段的模板
    public void filter(Class<?> clazz, String include, String exclude) {
        //如果没有这个类   自己返回
        if (clazz == null) {
            return;
        }
        //选择包含字段操作
        if (include != null && include.length() > 0) {
            objectMapper.setFilterProvider(new SimpleFilterProvider()
                    .addFilter(FIELD_INCLUDE, SimpleBeanPropertyFilter.filterOutAllExcept(include.split(","))));
            objectMapper.addMixIn(clazz, DynamicInclude.class);
        } else if (exclude != null && exclude.length() > 0) {
            objectMapper.setFilterProvider(new SimpleFilterProvider()
                    .addFilter(FIELD_EXCLUDE, SimpleBeanPropertyFilter.serializeAllExcept(exclude.split(","))));
            objectMapper.addMixIn(clazz, DynamicExclude.class);
        }
    }

    public String toJson(Object obeject) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obeject);
    }

}
