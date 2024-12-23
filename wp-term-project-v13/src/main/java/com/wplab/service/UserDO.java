package com.wplab.service;

public class UserDO {
	    private String user_id;
	    private String password;
	    private String email;

	    public UserDO() {
			// TODO Auto-generated constructor stub
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "UserDO [user_id=" + user_id + ", password=" + password + ", email=" + email + "]";
		}
	    
	    
}
