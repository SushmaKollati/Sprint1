package com.cg.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Respondant {
	@Id
		private Integer respondantId;
		private String name;
		private String emailId;
		private String password;
		public Respondant(Integer respondantId, String name, String emailId, String password) {
			super();
			this.respondantId = respondantId;
			this.name = name;
			this.emailId = emailId;
			this.password = password;
		}
		
		public Respondant() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getRespondantId() {
			return respondantId;
		}
		public void setRespondantId(Integer respondantId) {
			this.respondantId = respondantId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((respondantId == null) ? 0 : respondantId.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Respondant other = (Respondant) obj;
			if (emailId == null) {
				if (other.emailId != null)
					return false;
			} else if (!emailId.equals(other.emailId))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (respondantId == null) {
				if (other.respondantId != null)
					return false;
			} else if (!respondantId.equals(other.respondantId))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Respondant [respondantId=" + respondantId + ", name=" + name + ", emailId=" + emailId
					+ ", password=" + password + "]";
		}
		
		
}
