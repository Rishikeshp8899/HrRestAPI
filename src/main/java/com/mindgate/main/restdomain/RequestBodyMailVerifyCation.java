package com.mindgate.main.restdomain;

public class RequestBodyMailVerifyCation {
private int otp;
public RequestBodyMailVerifyCation() {}
public int getOtp() {
	return otp;
}
public void setOtp(int otp) {
	this.otp = otp;
}
public RequestBodyMailVerifyCation(int otp) {
	super();
	this.otp = otp;
}

}
