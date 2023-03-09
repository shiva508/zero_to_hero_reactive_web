package com.pool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_user_transactions")
public class UserTransaction implements Serializable {

    @Id
    @Column("TX_ID")
    private Long txId;
    @Column("USER_ID")
    private Long userId;
    @Column("AMOUNT")
    private Integer amount;
    @Column("TX_TIME")
    private LocalDateTime transactionTime;
}
