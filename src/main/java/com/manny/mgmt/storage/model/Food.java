package com.manny.mgmt.storage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(columnDefinition = "integer default 0")
	private int count;
	@Column(name = "is_set_alarm", columnDefinition = "boolean default False")
	private boolean isSetAlarm;
	@Column(name = "min_count", columnDefinition = "integer default 0")
	private int minCount;
	@Column(name = "purchase_date", nullable = false)
	private Date purchaseDate;
	@ManyToOne
	@JoinColumn(name = "section_id", nullable = false)
	@JsonBackReference
	private Section section;
	
	public Food() {}

    public Food(String name, int count, boolean isSetAlarm, int minCount, Date purchaseDate, Section section) {
		this.name = name;
		this.count = count;
		this.isSetAlarm = isSetAlarm;
		this.minCount = minCount;
		this.purchaseDate = purchaseDate;
		this.section = section;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean getIsSetAlarm() {
		return isSetAlarm;
	}
	
	public int getMinCount() {
		return minCount;
	}
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	
	public Section getSection() {
		return section;
	}
	
	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", count=" + count + ", isSetAlarm=" + isSetAlarm + ", minCount="
				+ minCount + ", purchaseDate=" + purchaseDate + ", section=" + section.getId() + "]";
	}
	
	

}
