package com.qmik.query;

import java.util.List;

public interface ConditionQuery extends Query {
	
	List<Condition<?>> getConditions();
	
}
