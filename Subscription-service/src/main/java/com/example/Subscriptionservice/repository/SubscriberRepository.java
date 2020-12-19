package com.example.Subscriptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Subscriptionservice.model.Subscriber;



@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {

	@Query(value = "select * from Subscriber where id = :id", nativeQuery = true)
	public Subscriber findSubscriberById(@Param(value = "id") Integer id);

}
