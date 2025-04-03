package com.yusufsahin.hiring_platform_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
@PrimaryKeyJoinColumn(name = "user_id")
public class Company extends User {

    @Column(nullable = false)
    private String companyName;

}
