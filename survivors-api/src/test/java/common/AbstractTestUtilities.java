package common;

import com.tnyagwaya.survivors.data.ResourceInfo;
import com.tnyagwaya.survivors.data.SurvivorInfo;
import com.tnyagwaya.survivors.survivor.Gender;
import com.tnyagwaya.survivors.survivor.Location;
import com.tnyagwaya.survivors.survivor.resource.AmmunitionType;
import com.tnyagwaya.survivors.survivor.resource.Constants;
import com.tnyagwaya.survivors.survivor.resource.ResourceType;
import com.tnyagwaya.survivors.survivor.resource.Unit;
import com.tnyagwaya.survivors.survivor.resource.WaterType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTestUtilities {

    protected SurvivorInfo getSurvivorInfo() {
        final SurvivorInfo survivorInfo = new SurvivorInfo();
        survivorInfo.setName("Thomas");
        survivorInfo.setLastName("Nyagwaya");
        survivorInfo.setSurvivorId("Survivor-test-2");
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

    protected ResourceInfo getAmmo() {
        final ResourceInfo ammo  = new ResourceInfo();
        ammo.setResourceType(ResourceType.AMMUNITION);
        ammo.setQuantity(new BigDecimal(20067));
        ammo.setUnit(Unit.COUNT);
        ammo.setDescription("10 caliber bullet armor piercing");
        ammo.getAttributes().addData(Constants.Ammunition.TYPE, AmmunitionType.PROJECTILE);
        return ammo;
    }

    protected ResourceInfo getWater() {
        final ResourceInfo info  = new ResourceInfo();
        info.setResourceType(ResourceType.WATER);
        info.setQuantity(new BigDecimal(2000));
        info.setUnit(Unit.LITRES);
        info.setDescription("Mineral water");
        info.getAttributes().addData(Constants.Water.TYPE, WaterType.MINERAL_WATER);
        return info;
    }

    protected ResourceInfo getRice() {
        final ResourceInfo info  = new ResourceInfo();
        info.setResourceType(ResourceType.FOOD);
        info.setQuantity(new BigDecimal(25));
        info.setUnit(Unit.KGS);
        info.setDescription("Rice");
        info.getAttributes().addData(Constants.Food.CALORIES, 30);
        return info;
    }

    protected ResourceInfo getCannedMeat() {
        final ResourceInfo info  = new ResourceInfo();
        info.setResourceType(ResourceType.FOOD);
        info.setQuantity(new BigDecimal(0.5));
        info.setUnit(Unit.KGS);
        info.setDescription("Bull Brand Canned Corned Meat");
        info.getAttributes().addData(Constants.Food.CALORIES, 50);
        return info;
    }
}
