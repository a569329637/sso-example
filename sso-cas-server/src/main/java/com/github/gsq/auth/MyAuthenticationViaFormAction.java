package com.github.gsq.auth;

import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.RootCasException;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyAuthenticationViaFormAction extends AuthenticationViaFormAction {
}
