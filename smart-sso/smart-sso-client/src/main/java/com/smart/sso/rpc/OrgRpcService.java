package com.smart.sso.rpc;

import java.util.List;


public interface OrgRpcService {


	public List<OrgRpcDto> findByBusiCode(String[] busiCodes);
}
