package com.tnyagwaya.survivors.robot;

import com.tnyagwaya.survivors.data.SurvivorInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RobotService {
    @Cacheable(cacheNames = {"robots"})
    RobotsInfo getRobots(final RobotCategory robotCategory);


}
