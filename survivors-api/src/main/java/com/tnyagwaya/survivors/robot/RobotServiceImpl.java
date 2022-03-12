package com.tnyagwaya.survivors.robot;

import com.tnyagwaya.survivors.data.SurvivorInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
@Slf4j
public class RobotServiceImpl implements RobotService {

    private final RestTemplate restTemplate;

    private final ConfigProperties robotProperties;

    @Override
    @Cacheable(cacheNames = {"robots"})
    public RobotsInfo getRobots(final RobotCategory robotCategory) {
        log.info("fetching robots");
        final RobotsInfo robotInfo = new RobotsInfo();
        restTemplate.exchange(
                        robotProperties.getRobotCpuUrl(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Robot>>() {
                        }).getBody()
                .stream()
                .filter(robotPredicate(robotCategory))
                .forEach(robotInfo::add);
        return robotInfo;
    }



    private Predicate<Robot> robotPredicate(RobotCategory robotCategory) {
        return r-> RobotCategory.All== robotCategory || r.getCategory()== robotCategory;
    }
}
