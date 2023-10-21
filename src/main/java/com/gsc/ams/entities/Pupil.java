package com.gsc.ams.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.gsc.ams.entities.Teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity

public class Pupil {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Firstname is mandatory")
    @Column(name = "firstname")
    private String firstname;
    
    @NotBlank(message = "Lastname is mandatory")
    @Column(name = "lastname")
    private String lastname;
    
    @Column(name = "phone")
    private int phone;
    
    @Column(name = "email")
    private String email;
 
    @Column(name = "age")
    private float age;
    
  

    public Pupil() {}

    public Pupil(String lastname,String email, float age,  String firstname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age= age;
        }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	public float getAge() {
		return age;
	}
	public void setAge(float age) {
		this.age = age;
	}
}



