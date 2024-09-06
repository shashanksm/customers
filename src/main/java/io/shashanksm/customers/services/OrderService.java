package io.shashanksm.customers.services;

import java.util.List;
import java.util.Optional;

import io.shashanksm.customers.dtos.OrderDto;

public interface OrderService {
	
	public Optional<OrderDto> createOrder(OrderDto orderDto);
	
	public Optional<OrderDto> findOrderById(Long id);
	
	public List<OrderDto> findOrderByAccountId(Long accountId);
	
	public List<OrderDto> findAllOrders(int pageNumber, int countPerPage);
	
	public Optional<OrderDto> updateOrder(Long id, OrderDto orderDto);
	
	public Optional<OrderDto> deleteOrder(Long id);
	
}
