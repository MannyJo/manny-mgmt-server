package com.manny.mgmt.storage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "storage_id", nullable = false)
	@JsonBackReference
	private Storage storage;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "section")
	@JsonManagedReference
	private final List<Food> foods = new ArrayList<>();
	
	public Section() {}

    public Section(String name, Storage storage) {
		this.name = name;
		this.storage = storage;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Storage getStorage() {
		return storage;
	}
	
	public List<Food> getFoods() {
		return foods;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", name=" + name + ", storage=" + storage + ", foods.size()=" + foods.size() + "]";
	}

}
