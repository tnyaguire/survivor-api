package com.tnyagwaya.survivors.survivor;

import com.tnyagwaya.survivors.common.BaseEntity;
import com.tnyagwaya.survivors.survivor.resource.Resource;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "survivor")
public class Survivor extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name="dob")
    private LocalDate dob;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="survivor_id", unique = true)
    private String survivorId;

    @Embedded
    private Location lastLocation;

    @OneToMany(mappedBy = "survivor")
    private List<Resource> resources;

    private int infectionFlagCount;

    @Column(name="infected")
    private boolean infected;

}
