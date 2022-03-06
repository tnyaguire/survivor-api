package com.tnyagwaya.survivors.robot;

import org.springframework.cache.annotation.Cacheable;

public interface RobotService {
    @Cacheable(cacheNames = {"robots"})
    RobotsInfo getRobots(final RobotCategory robotCategory);
}
