package com.geminicode.site.initialization;

import com.geminicode.site.model.Example;
import com.geminicode.site.service.ExampleService;
import com.geminicode.site.service.ServiceSingleton;

import javax.servlet.http.HttpServlet;

public class CommonInitServlet extends HttpServlet {

	private static final long serialVersionUID = 5858549967053237300L;

	protected final static ExampleService EXAMPLE_SERVICE = ServiceSingleton.getExampleService();

	protected static final String EXAMPLE = "example";

	protected Example newExample(long id, final String name) {
		Example example = new Example();
		example.setId(id);
		example.setName(name);

		return example;
	}
}
