package com.example.Subscriptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Subscriptionservice.entity.subscription;
import com.example.Subscriptionservice.model.Subscription;

@Repository
public interface Subscriptionrepository extends JpaRepository<subscription, Integer>{
	@Query(value = "select * from Subscription where subscription_id = :subscription_id and subscriber_id = :subscriber_id", nativeQuery = true)
	public Subscription findSubscriberByBookId(@Param(value = "subscription_id") Integer subscription_id, @Param(value = "subscriber_id") Integer subscriber_id);

	public void save(Subscription existingSubscription);
	
}
