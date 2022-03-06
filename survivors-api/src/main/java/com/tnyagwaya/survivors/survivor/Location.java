package com.tnyagwaya.survivors.survivor;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
public class Location {

    @Column(name="latitude")
    private BigDecimal latitude;

    @Column(name="longitude")
    private BigDecimal longitude;
}
