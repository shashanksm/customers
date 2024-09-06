package io.shashanksm.customers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.shashanksm.customers.dtos.OrderDto;
import io.shashanksm.customers.entities.Order;
import io.shashanksm.customers.repositories.OrderRepository;

@Service
public class StandardJpaOrderService implements OrderService{

	private static final Logger logger = LoggerFactory.getLogger(StandardJpaOrderService.class);
	
	private OrderRepository orderRepository;
	
	public StandardJpaOrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Override
	public Optional<OrderDto> createOrder(OrderDto orderDto) {
		
		Optional<OrderDto> ret = Optional.empty();
		
		logger.info("creating order for account with id : "+orderDto.getAccount().getId());
		
		Order createdOrder =  this.orderRepository.save(orderDto.toEntity());
		
		ret = Optional.of(OrderDto.fromEntity(createdOrder));
		
		return ret;
	}

	@Override
	public Optional<OrderDto> findOrderById(Long id) {
		Optional<OrderDto> ret = Optional.empty();
		
		logger.info("finding order with id : "+id);
		
		ret = this.orderRepository.findById(id)
				.map(OrderDto::fromEntity);
		
		return ret;
	}

	@Override
	public List<OrderDto> findOrderByAccountId(Long accountId) {
		List<OrderDto> ret = new ArrayList<>();
		
		ret = this.orderRepository.findByAccountId(accountId)
				.stream()
				.map(OrderDto::fromEntity)
				.collect(Collectors.toList());
		
		return ret;
	}

	@Override
	public List<OrderDto> findAllOrders(int pageNumber, int countPerPage) {
		List<OrderDto> ret = new ArrayList<>();
		
		ret = this.orderRepository.findAll(PageRequest.of(pageNumber, countPerPage))
		.stream()
		.map(OrderDto::fromEntity)
		.collect(Collectors.toList());
		
		return ret;
	}

	@Override
	public Optional<OrderDto> updateOrder(Long id, OrderDto orderDto) {
		Optional<OrderDto> ret = Optional.empty();
		
		if(this.orderRepository.existsById(id)) {
			
			logger.info("updating order with id "+id);
			
			Optional<Order> foundOrderOpt = this.orderRepository.findById(id);
			
			Order foundOrder = foundOrderOpt.get();
			
			foundOrder.setCreated(orderDto.getCreated());
			foundOrder.setStatus(orderDto.getStatus());
			foundOrder.setType(orderDto.getType());
			
			Order updatedOrder = this.orderRepository.save(foundOrder);
			
			ret = Optional.of(updatedOrder)
					.map(OrderDto::fromEntity);
			
			
		}else {
			
			logger.warn("order with id : "+id+" does not exist");
			logger.info("updated nothing");
			
		}
		
		return ret;
	}

	@Override
	public Optional<OrderDto> deleteOrder(Long id) {
		Optional<OrderDto> ret = Optional.empty();
		
		if(this.orderRepository.existsById(id)) {
			
			logger.info("deleting order with id "+id);
			
			Optional<Order> foundOrderOpt = this.orderRepository.findById(id);
			
			Order foundOrder = foundOrderOpt.get();
			
			ret = Optional.of(foundOrder)
					.map(OrderDto::fromEntity);
			
			this.orderRepository.delete(foundOrder);
			
		}else {
			
			logger.warn("order with id : "+id+" does not exist");
			logger.info("deleted nothing");
			
		}
		
		return ret;
	}
	
}
