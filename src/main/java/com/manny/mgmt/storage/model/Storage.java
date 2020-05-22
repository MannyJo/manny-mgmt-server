package com.manny.mgmt.storage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.manny.mgmt.user.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Storage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "storage", fetch = FetchType.LAZY)
	@JsonManagedReference
	@OrderBy(value = "id")
	private final List<Section> sections = new ArrayList<>();
	@ManyToMany
	@JoinTable(
			name = "user_storage",
			joinColumns = @JoinColumn(name = "storage_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	@JsonIgnoreProperties("storage")
	private List<User> users = new ArrayList<>();
	
	protected Storage() {}

    public Storage(String name, List<User> users) {
		this.name = name;
		this.users = users;
		this.users.forEach(x -> x.getStorage().add(this));
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public List<Section> getSections() {
		return sections;
	}

	public List<User> getUsers() {
		return users;
	}

	@Override
	public String toString() {
		return "Storage{" +
				"id=" + id +
				", name='" + name + '\'' +
				", sections=" + sections +
				", users=" + users +
				'}';
	}
}
