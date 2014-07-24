package com.geminicode.site.dao.impl;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.geminicode.site.model.impl.ObjectifyExample;

public class ObjectifySingleton {

	static {

		ObjectifyService.register(ObjectifyExample.class);
	}

	public static Objectify get() {
		final Objectify ofy = ObjectifyService.ofy();
		ofy.clear();
		return ofy;
	}
}
