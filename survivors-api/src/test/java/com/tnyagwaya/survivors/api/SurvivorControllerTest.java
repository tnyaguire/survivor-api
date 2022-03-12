package com.tnyagwaya.survivors.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnyagwaya.survivors.data.ResourceInfo;
import com.tnyagwaya.survivors.data.SurvivorInfo;
import com.tnyagwaya.survivors.survivor.Gender;
import com.tnyagwaya.survivors.survivor.Location;
import com.tnyagwaya.survivors.survivor.SurvivorService;
import com.tnyagwaya.survivors.survivor.resource.AmmunitionType;
import com.tnyagwaya.survivors.survivor.resource.Constants;
import com.tnyagwaya.survivors.survivor.resource.ResourceType;
import com.tnyagwaya.survivors.survivor.resource.Unit;
import com.tnyagwaya.survivors.survivor.resource.WaterType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = {SurvivorController.class})
class SurvivorControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SurvivorService survivorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAddSurvivorIfRequestIsValid() throws Exception {
        final SurvivorInfo info = createValidSurvivor();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/survivors")
                        .content(objectMapper.writeValueAsString(info))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private SurvivorInfo createValidSurvivor() {
        final SurvivorInfo survivorInfo = new SurvivorInfo();
        survivorInfo.setName("Thomas");
        survivorInfo.setLastName("Nyagwaya");
        survivorInfo.setSurvivorId("Survivor01");
        survivorInfo.setCreatedBy("survivor00");
        survivorInfo.setLocation(new Location(new BigDecimal(-17.82772), new BigDecimal(31.05337)));
        survivorInfo.setGender(Gender.MALE);
        survivorInfo.setDob(LocalDate.of(1983, 11, 24));

        List<ResourceInfo> resources = new ArrayList<>();
        resources.add(getAmmo());
        resources.add(getWater());
        resources.add(getCannedMeat());
        resources.add(getRice());
        survivorInfo.setResources(resources);
        return survivorInfo;
    }

    private ResourceInfo getAmmo() {
        final ResourceInfo ammo  = new ResourceInfo();
        ammo.setResourceType(ResourceType.AMMUNITION);
        ammo.setQuantity(new BigDecimal(20067));
        ammo.setUnit(Unit.COUNT);
        ammo.setDescription("10 caliber bullet armor piercing");
        ammo.getAttributes().addData(Constants.Ammunition.TYPE, AmmunitionType.PROJECTILE);
        return ammo;
    }

    private ResourceInfo getWater() {
        final ResourceInfo info  = new ResourceInfo();
        info.setResourceType(ResourceType.WATER);
        info.setQuantity(new BigDecimal(2000));
        info.setUnit(Unit.LITRES);
        info.setDescription("Mineral water");
        info.getAttributes().addData(Constants.Water.TYPE, WaterType.MINERAL_WATER);
        return info;
    }

    private ResourceInfo getRice() {
        final ResourceInfo info  = new ResourceInfo();
        info.setResourceType(ResourceType.FOOD);
        info.setQuantity(new BigDecimal(25));
        info.setUnit(Unit.KGS);
        info.setDescription("Rice");
        info.getAttributes().addData(Constants.Water.TYPE, WaterType.MINERAL_WATER);
        return info;
    }

    private ResourceInfo getCannedMeat() {
        final ResourceInfo info  = new ResourceInfo();
        info.setResourceType(ResourceType.FOOD);
        info.setQuantity(new BigDecimal(0.5));
        info.setUnit(Unit.KGS);
        info.setDescription("Bull Brand Canned Corned Meat");
        info.getAttributes().addData(Constants.Water.TYPE, WaterType.MINERAL_WATER);
        return info;
    }

}