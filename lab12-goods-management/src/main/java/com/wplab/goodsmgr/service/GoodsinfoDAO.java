package com.wplab.goodsmgr.service;

import java.util.List;

public interface GoodsinfoDAO {
	
	int insertGoods(GoodsDO goods);
	int updateGoods(GoodsDO goods);
	int deleteGoods(GoodsDO goods);
	GoodsDO getGoods(GoodsDO goods);
	List<GoodsDO> listGoods();
	
}
