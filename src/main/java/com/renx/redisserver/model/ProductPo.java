package com.renx.redisserver.model;

import lombok.Data;

import javax.persistence.*;


@Entity  // 该注解声明一个实体类，与数据库中的表对应
@Table(name="t_product")
public class ProductPo {
	@Id   // 表明id
	@GeneratedValue(strategy= GenerationType.IDENTITY)   //  自动生成
	private Integer id ;
	@Column(name="product_name")
	private String productName;
	private Integer stock;
	private Double price;
	private Integer version;
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
