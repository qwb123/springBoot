package com.qwb.demo.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qwb.demo.DemoApplicationTests;
import com.qwb.demo.model.entity.SellerInfo;
import org.junit.Test;

public class FilterJsonTest extends DemoApplicationTests {

    private JsonFilterSerializer jsonFilterSerializer;

    @Test
    public void testFilterJson() throws JsonProcessingException {
        jsonFilterSerializer = new JsonFilterSerializer();
        jsonFilterSerializer.filter(SellerInfo.class, null, "id");
        System.out.println(jsonFilterSerializer.toJson(new SellerInfo("1", "123", "4321", "123", null, null)));
    }
}
