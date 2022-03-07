package com.tnyagwaya.survivors.survivor.resource;

import com.tnyagwaya.survivors.data.ResourceInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WaterResourceFactory extends ResourceFactory{

    @Override
    public boolean supports(ResourceInfo resourceInfo) {
        return ResourceType.WATER==resourceInfo.getResourceType();
    }

    @Override
    public Resource create(ResourceInfo resourceInfo) {
        final Water resource = new Water();
        final ResourceAttributes attributes = resourceInfo.getAttributes();
        resource.setType(attributes.getAs(Constants.Water.TYPE, WaterType.class));
        return resource;
    }
}
