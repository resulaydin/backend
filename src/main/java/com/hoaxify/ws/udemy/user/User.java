package com.hoaxify.ws.udemy.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.udemy.shared.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "userrs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	@NotNull(message = "{hoaxify.username.constraints.NOTNULL.message}")
	@UniqueUsername
	@JsonView(Views.Base.class)
	private String username;

	@NotNull
	@Column(name = "displayName")
	private String displayName;

	@Column(name = "password")
	@NotNull
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",message = "{hoaxify.password.constraints.PATTERN.message}")
	@JsonView(Views.Base.class)
	private String password;

	@JsonView(Views.Base.class)
	private String image;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("Role_user ");
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
