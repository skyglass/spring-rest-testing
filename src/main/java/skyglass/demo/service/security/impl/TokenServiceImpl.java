package skyglass.demo.service.security.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import skyglass.demo.data.model.security.Token;
import skyglass.demo.data.security.TokenData;
import skyglass.demo.service.GenericServiceImpl;
import skyglass.demo.service.security.TokenService;

@Repository
@Transactional(readOnly = true)
public class TokenServiceImpl extends GenericServiceImpl<Token, String, TokenData> 
			implements TokenService {

}
