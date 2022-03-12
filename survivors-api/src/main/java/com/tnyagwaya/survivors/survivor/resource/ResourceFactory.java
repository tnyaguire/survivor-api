package com.tnyagwaya.survivors.survivor.resource;

import com.tnyagwaya.survivors.data.ResourceInfo;
import com.tnyagwaya.survivors.survivor.Survivor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ResourceFactory {

    public int order(){
        return Integer.MIN_VALUE;
    }

    public abstract boolean supports(final ResourceInfo resourceInfo);

    public Resource create(final ResourceInfo resourceInfo, final Survivor survivor, String reportedBy){
        final Resource resource = create(resourceInfo);
        resource.setSurvivor(survivor);
        resource.setCreatedBy(reportedBy);
        resource.setDescription(resourceInfo.getDescription());
        resource.setQuantity(resourceInfo.getQuantity());
        resource.setUnit(resourceInfo.getUnit());
        resource.setResourceCategory(resourceInfo.getResourceType());
        log.info("Creating resource: {}", resource);
        return resource;
    }

    public Resource create(ResourceInfo resourceInfo) {
        return new Resource();
    }
}
