package com.psl.kyc.model.customer;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(length = 255, nullable = false)
    private String forename;

    @Column(length = 255, nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @ElementCollection
    @CollectionTable(name = "account_customer",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"))
    @Column(name = "account_id")
    private List<String> accounts = new ArrayList<>();
}
