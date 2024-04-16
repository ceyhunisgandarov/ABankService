package com.bank.api.abankservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit_tbl")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String agreementNumber;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private BigDecimal creditAmount;
    private BigDecimal remainingDept;
    private BigDecimal interest;
    private BigDecimal period;
    @ColumnDefault(value = "1")
    private int type;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;
    @ColumnDefault(value = "1")
    private int active;


}
