package com.gsq.sso.action;

import com.google.code.kaptcha.Constants;
import com.gsq.sso.credential.UsernamePasswordCaptchaCredential;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.web.flow.AuthenticationViaFormAction;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;
import org.springframework.webflow.execution.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/11/15.
 * 自定义验证码处理逻辑action
 */
public class AuthenticationViaCaptchaFormAction extends AuthenticationViaFormAction {

    public final String validateCaptcha(final RequestContext context, final Credential credential, final MessageContext messageContext){
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        HttpSession session = request.getSession();
        String sessionCaptcha = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        UsernamePasswordCaptchaCredential upc = (UsernamePasswordCaptchaCredential)credential;
        String captcha = upc.getCaptcha();
        System.out.println("获取Session验证码-->" + sessionCaptcha);
        System.out.println("获取表单输入信息-->" + upc);
        if(!StringUtils.hasText(sessionCaptcha) || !StringUtils.hasText(captcha)){
            messageContext.addMessage(new MessageBuilder().error().code("required.captcha").build());
            return "error";
        }
        if(captcha.equals(sessionCaptcha)){
            return "success";
        }
        //这段网上这么写的messageContext.addMessage(new MessageBuilder().code("required.captcha").build());
        //实际上这么写是org.springframework.binding.message.INFO级别的，这会导致前台表单无法显示这里的错误信息
        messageContext.addMessage(new MessageBuilder().error().code("error.authentication.captcha.bad").build());
        return "error";
    }
}
