package com.Orderservice.OrderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Orderservice.OrderService.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer>{

}
