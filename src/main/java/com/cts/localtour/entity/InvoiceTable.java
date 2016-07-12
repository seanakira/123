package com.cts.localtour.entity;
// Generated 2016-7-12 12:02:55 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * InvoiceTable generated by hbm2java
 */
@Entity
@Table(name = "invoiceTable", catalog = "localtour")
public class InvoiceTable implements java.io.Serializable {

	private InvoiceTableId id;

	public InvoiceTable() {
	}

	public InvoiceTable(InvoiceTableId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "Id", nullable = false)),
			@AttributeOverride(name = "incomeId", column = @Column(name = "incomeId", nullable = false)),
			@AttributeOverride(name = "invoiceName", column = @Column(name = "invoiceName", nullable = false, length = 40)),
			@AttributeOverride(name = "invoiceContent", column = @Column(name = "invoiceContent", nullable = false, length = 10)),
			@AttributeOverride(name = "invoiceNo", column = @Column(name = "invoiceNo", nullable = false)),
			@AttributeOverride(name = "requestUserId", column = @Column(name = "requestUserId", nullable = false)),
			@AttributeOverride(name = "invoiceAmount", column = @Column(name = "invoiceAmount", nullable = false, precision = 12, scale = 0)),
			@AttributeOverride(name = "openDate", column = @Column(name = "openDate", nullable = false, length = 10)),
			@AttributeOverride(name = "openUserId", column = @Column(name = "openUserId", nullable = false)),
			@AttributeOverride(name = "isLend", column = @Column(name = "isLend", nullable = false)) })
	public InvoiceTableId getId() {
		return this.id;
	}

	public void setId(InvoiceTableId id) {
		this.id = id;
	}

}
