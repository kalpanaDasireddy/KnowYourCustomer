package com.psl.kyc.model.account;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false)
    private Integer accountNumber;

    @ElementCollection
    @CollectionTable(name = "account_customer",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account_id"))
    @Column(name = "customer_id")
    private List<String> customers = new ArrayList<>();
}
