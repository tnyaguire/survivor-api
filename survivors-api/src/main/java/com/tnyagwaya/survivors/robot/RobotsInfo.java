package com.tnyagwaya.survivors.robot;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class RobotsInfo implements Serializable {
    private List<Robot> robots = new ArrayList<>();
    public void add(Robot robot){
        robots.add(robot);
    }
}
