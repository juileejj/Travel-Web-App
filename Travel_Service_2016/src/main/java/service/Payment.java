package service;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="paymenttable")
	public class Payment {

		@Id
		@GeneratedValue
		@Column(name="paymentid")
		private int paymentId;
		@Column(name="paymentdate")
		private String paymentDate;
		@Column(name="paidstatus")
		private String paidstatus;
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="cardid",nullable=false)
		private Card card;
		
		
		@Column(name ="totalprice")
		private double totalprice;
		public Card getCard() {
			return card;
		}
		public void setCard(Card card) {
			this.card = card;
		}
		public double getTotalprice() {
			return totalprice;
		}
		public void setTotalprice(double totalprice) {
			this.totalprice = totalprice;
		}
		public int getPaymentId() {
			return paymentId;
		}
		public void setPaymentId(int paymentId) {
			this.paymentId = paymentId;
		}
		public String getPaymentDate() {
			return paymentDate;
		}
		public void setPaymentDate(String paymentDate) {
			this.paymentDate = paymentDate;
		}
		public String getPaidstatus() {
			return paidstatus;
		}
		public void setPaidstatus(String paidstatus) {
			this.paidstatus = paidstatus;
		}
	}

