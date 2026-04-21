package com.epam.practice.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


public class NplusoneproblemExample {
	
	private static HostelRepo hostelRepo;
	public NplusoneproblemExample(HostelRepo hostelRepo) {
		this.hostelRepo = hostelRepo;
	}
	
	public static void main(String[] args) {
		List<Hostel> hostels = hostelRepo.findAll();
	}
}

/*
 * Instead of 1 query hibernate executes N+1
 * 1 query for parent
 * N queries for child
 */



@Entity
class Hostel{
	
	@Id
	private Long id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Room> rooms ;
}

@Entity
class Room{
	private Long id;
	private String name;
	private int ocupancy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id" , nullable = false )
	private Hostel hostel;
}


interface HostelRepo extends JpaRepository<Hostel, Long>{
	
	@Query("SELECT h from hostel h JOIN FETCH h.rooms")
	List<Hostel> findAll(); 
	
	/*
	 * Without any annotation or solution this query results in N+1 issue
	 * 1. Use Fetch Join
	 * 2. @EntityGraph
	 * 3. batch fetching
	 */
}
