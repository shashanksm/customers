package io.shashanksm.customers.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

import io.shashanksm.customers.entities.Order;

public class OrderDto {

	private Long id;

	private String status;

	private LocalDateTime created;

	private String type;

	private AccountDto account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	public OrderDto(Long id, String status, LocalDateTime created, String type, AccountDto account) {
		super();
		this.id = id;
		this.status = status;
		this.created = created;
		this.account = account;
		this.type = type;
	}

	public OrderDto() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDto other = (OrderDto) obj;
		return Objects.equals(account, other.account) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", status=" + status + ", created=" + created + ", type=" + type + ", account="
				+ account + "]";
	}

	public Order toEntity() {

		Order order = new Order();

		if (id != null)
			order.setId(id);

		order.setCreated(created);

		order.setStatus(status);

		order.setType(status);

		order.setAccount(account.toEntity());

		return order;

	}

	public static OrderDto fromEntity(Order order) {
		OrderDto orderDto = new OrderDto();

		orderDto.id = order.getId();
		orderDto.account = AccountDto.fromEntity(order.getAccount());
		orderDto.created = order.getCreated();
		orderDto.type = order.getType();
		orderDto.status = order.getStatus();

		return orderDto;
	}

}
