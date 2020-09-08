package Java_Classes;

import java.util.Date;

public class Message {

		//instance variables
		private int messageNum;
		private String sender;
		private String reciver;
		private String content;
		private Date sendTime;
		
		
		//constructors
		public Message(String sender, String reciver, String content, Date sendTime) {
			this.sender = sender;
			this.reciver = reciver;
			this.content = content;
			this.sendTime = sendTime;
		}


		public String getSender() {
			return sender;
		}


		public void setSender(String sender) {
			this.sender = sender;
		}


		public String getReciver() {
			return reciver;
		}


		public void setReciver(String reciver) {
			this.reciver = reciver;
		}


		public String getContent() {
			return content;
		}


		public void setContent(String content) {
			this.content = content;
		}


		public Date getSendTime() {
			return sendTime;
		}


		public void setSendTime(Date sendTime) {
			this.sendTime = sendTime;
		}
		
		
		
		

		
		
		
		
}
