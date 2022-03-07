package com.tnyagwaya.survivors.survivor.resource;

import com.tnyagwaya.survivors.data.ResourceInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GenericResourceFactory extends ResourceFactory {
    public int order() {
        return Integer.MAX_VALUE;
    }
    @Override
    public boolean supports(ResourceInfo resourceInfo) {
        return true;
    }
}
