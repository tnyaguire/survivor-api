package com.tnyagwaya.survivors.survivor.resource;

import com.tnyagwaya.survivors.data.ResourceInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MedicationResourceFactory extends ResourceFactory{
    @Override
    public boolean supports(ResourceInfo resourceInfo) {
        return ResourceType.MEDICATION==resourceInfo.getResourceType();
    }

    @Override
    public Resource create(ResourceInfo resourceInfo) {
        final Medication resource = new Medication();;
        final ResourceAttributes attributes = resourceInfo.getAttributes();
        resource.setQuantity(attributes.getAs("quantity", BigDecimal.class));
        resource.setDescription(attributes.getAs("description", String.class));
        return resource;
    }
}
