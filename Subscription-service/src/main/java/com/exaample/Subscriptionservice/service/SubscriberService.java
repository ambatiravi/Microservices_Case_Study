package com.exaample.Subscriptionservice.service;

import java.awt.print.Book;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.casestudy.bookService.exception.ApplicationException;
import com.example.Subscriptionservice.model.Subscriber;
import com.example.Subscriptionservice.model.Subscription;
import com.example.Subscriptionservice.repository.SubscriberRepository;
import com.example.Subscriptionservice.repository.Subscriptionrepository;


@Service
@PropertySource("classpath:App-url.properties")
public class SubscriberService {

	@Value(value = "${bookservice.getbook.url}")
	private String get_book_url;
	
	@Value(value = "${bookservice.update.url}")
	private String update_book_url;
	
	@Autowired
	SubscriberRepository subscriberRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Subscriptionrepository subscriptionRepository;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public List<Subscriber> findAllSubscribers() {
		
		List<Subscriber> subscriberList = subscriberRepository.findAll();
		if(subscriberList == null || subscriberList.size() == 0) {
			throw new ApplicationException();
		}
		return subscriberList;
	}

	public Subscriber findSubscriberById(Integer id) {
		
		Subscriber subscriber = subscriberRepository.findSubscriberById(id);
		
		if(subscriber == null) {
			throw new ApplicationException("Subscriber with Id " + id + " is not found");
		}
		
		return subscriber;
	}
	

	@Transactional
	private void updateBookStock(Integer bookId, String type) {
		
		String serviceURL = update_book_url + "id=" + bookId + "&type=" + type ;

		restTemplate.exchange(serviceURL, HttpMethod.POST, null,
				new ParameterizedTypeReference<Book>() {
				});

	}

	@Transactional
	public void insertSubscription(Subscription subscription) {
		
		Subscription subscrip= subscriptionRepository.findSubscriberByBookId(subscription.getSubscriptionId(), subscription.getSubscriberId());
		
		}
		
	

	@Transactional
	public void subscriptionCloser(Subscription subscription) {
		
		Subscription existingSubscription = subscriptionRepository.findSubscriberByBookId(subscription.getSubscriptionId(), subscription.getSubscriberId());
		
		if(existingSubscription != null) {
			existingSubscription.setReturnedDate(subscription.getReturnedDate());
			subscriptionRepository.save(existingSubscription);
			
		}
	}
	}
	
	
