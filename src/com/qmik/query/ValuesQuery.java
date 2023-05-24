package com.qmik.query;

import java.util.List;

import com.qmik.model.Model;

public interface ValuesQuery<T extends Model> extends Query<T> {
	
	List<?> getValues();
	
}
