package com.tnyagwaya.survivors.survivor.resource;

import com.tnyagwaya.survivors.data.ResourceInfo;
import com.tnyagwaya.survivors.survivor.Survivor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ResourceFactory {

    public abstract boolean supports(final ResourceInfo resourceInfo);

    public Resource create(final ResourceInfo resourceInfo, final Survivor survivor, String reportedBy){
        final Resource resource = create(resourceInfo);
        resource.setSurvivor(survivor);
        resource.setCreatedBy(reportedBy);
        resource.setDescription(resourceInfo.getDescription());
        resource.setQuantity(resourceInfo.getQuantity());
        resource.setUnits(resourceInfo.getUnits());
        log.info("Creating resource: {}", resource);
        return resource;
    }

    public abstract Resource create(final ResourceInfo resourceInfo);
}
