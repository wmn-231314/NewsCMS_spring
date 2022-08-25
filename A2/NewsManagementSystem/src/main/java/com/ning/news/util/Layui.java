package com.ning.news.util;

import java.util.HashMap;
import java.util.List;

public class Layui extends HashMap<String, Object> {
	public static Layui data(Integer count, List<?> data,String msg) {
		Layui r = new Layui();
		r.put("code", 0);
		r.put("msg", msg);
		r.put("count", count);
		r.put("data", data);
		return r;
	}
}
