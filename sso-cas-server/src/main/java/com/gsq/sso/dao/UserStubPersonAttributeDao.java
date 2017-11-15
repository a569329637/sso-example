package com.gsq.sso.dao;

import com.gsq.sso.model.User;
import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AttributeNamedPersonImpl;
import org.jasig.services.persondir.support.StubPersonAttributeDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/15.
 * 自定义的返回给客户端相关信息
 */
@Component(value="attributeRepository")
public class UserStubPersonAttributeDao extends StubPersonAttributeDao {
    @Resource
    private UserDaoJdbc userDaoJdbc;
    @Override
    public IPersonAttributes getPerson(String uid) {
        Map<String, List<Object>> attributes = new HashMap<String, List<Object>>();
        try {
            User user = userDaoJdbc.getByUsername(uid);
            attributes.put("userId", Collections.singletonList((Object) user.getId()));
            attributes.put("username", Collections.singletonList((Object)user.getUsername()));
            attributes.put("password", Collections.singletonList((Object)user.getPassword()));
            attributes.put("salt", Collections.singletonList((Object)user.getSalt()));
            attributes.put("locked", Collections.singletonList((Object)user.getLocked()));
            attributes.put("desc", Collections.singletonList((Object)URLEncoder.encode("中文描述", "UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AttributeNamedPersonImpl(attributes);
    }
}
