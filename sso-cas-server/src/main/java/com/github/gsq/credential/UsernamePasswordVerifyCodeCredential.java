package com.github.gsq.credential;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jasig.cas.authentication.UsernamePasswordCredential;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsernamePasswordVerifyCodeCredential extends UsernamePasswordCredential {

    @NotNull
    @Size(min = 1, message = "required.verifyCode")
    private String verifyCode;

    UsernamePasswordVerifyCodeCredential() {
    }

    UsernamePasswordVerifyCodeCredential(String userName, String password, String code) {
        super(userName, password);
        this.verifyCode = verifyCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return this.getUsername() + " : " + this.getPassword() + " : " + this.getVerifyCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            UsernamePasswordVerifyCodeCredential that = (UsernamePasswordVerifyCodeCredential)o;
            if (this.getPassword() != null) {
                if (!this.getPassword().equals(that.getPassword())) {
                    return false;
                }
            } else if (that.getPassword() != null) {
                return false;
            }

            if (this.getUsername() != null) {
                if (this.getUsername().equals(that.getUsername())) {
                    return true;
                }
            } else if (that.getUsername() == null) {
                return true;
            }

            if (this.getVerifyCode() != null) {
                if (this.getVerifyCode().equals(that.getVerifyCode())) {
                    return true;
                }
            } else if (that.getVerifyCode() == null) {
                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getUsername())
                .append(getPassword()).append(verifyCode).toHashCode();
    }
}
