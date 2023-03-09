package com.pool.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_user_profile")
public class UserProfile implements Serializable {
	@Serial
	private static final long serialVersionUID = 7823610699711947019L;

	@Id
	@Column("USER_ID")
	private Long userId;

	@Column("AVATHAR_ID")
	private String avatharId;

	@Column("USER_NAME")
	private String username;

	@Column("PASSWORD")
	private String password;

	@Column("FIRST_NAME")
	private String firstName;

	@Column("LAST_NAME")
	private String lastName;

	@Column("CREATED_AT")
	private LocalDateTime createdAt;

	@Column("UPDATED_AT")
	private LocalDateTime updatedAt;

	@Column("BALANCE")
	private Integer balance;
}
