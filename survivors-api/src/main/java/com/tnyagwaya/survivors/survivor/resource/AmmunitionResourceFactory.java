package com.tnyagwaya.survivors.survivor.resource;

import com.tnyagwaya.survivors.data.ResourceInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmmunitionResourceFactory extends ResourceFactory{
    @Override
    public boolean supports(ResourceInfo resourceInfo) {
        return ResourceType.AMMUNITION==resourceInfo.getResourceType();
    }

    @Override
    public Resource create(ResourceInfo resourceInfo) {
        final Ammunition ammunition = new Ammunition();
        final ResourceAttributes attributes = resourceInfo.getAttributes();
        ammunition.setType(attributes.getAs(Constants.Ammunition.TYPE, AmmunitionType.class));
        return ammunition;
    }
}
