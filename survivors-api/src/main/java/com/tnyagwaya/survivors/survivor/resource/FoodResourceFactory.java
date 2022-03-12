package com.tnyagwaya.survivors.survivor.resource;

import com.tnyagwaya.survivors.data.ResourceInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FoodResourceFactory extends ResourceFactory{
    @Override
    public boolean supports(ResourceInfo resourceInfo) {
        return ResourceType.FOOD==resourceInfo.getResourceType();
    }

    @Override
    public Resource create(ResourceInfo resourceInfo) {
        final Food resource = new Food();
        final ResourceAttributes attributes = resourceInfo.getAttributes();
        resource.setCalories(new BigDecimal(attributes.getAs(Constants.Food.CALORIES, String.class)));
        return resource;
    }
}
