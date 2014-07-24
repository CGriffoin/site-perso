package com.geminicode.site.initialization;

import com.google.common.collect.Lists;
import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.model.Example;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InitLightServlet extends DataInitLightServlet {

	private static final Logger LOGGER = Logger.getLogger(InitLightServlet.class.getSimpleName());
	private static final long serialVersionUID = 5000586555501820290L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPost -- begin");
		try {

			initExamples();

		} catch (Exception e) {
			final String s = "Unable to init database";

			LOGGER.error(s, e);
			throw new ServletException(s, e);
		}

		LOGGER.info("doPost -- end");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doDelete -- begin");

		try {
				deleteExample();

		} catch (Exception e) {
			final String s = "Unable to clear database";

			LOGGER.error(s, e);
			throw new ServletException(s, e);
		}

		LOGGER.info("doDelete -- end");
	}

	private void deleteExample() throws NotFoundException, IOException {
		for (final Example example : EXAMPLE_SERVICE.list()) {
			EXAMPLE_SERVICE.delete(example.getId());
		}
	}

	private List<Example> initExamples() throws Exception {
		LOGGER.info(">>>>>> Examples - begin");

		final Example example1 = newExample(1L, "Example 1");
		EXAMPLE_SERVICE.create(example1);

		final Example example2 = newExample(2L, "Example 2");
		EXAMPLE_SERVICE.create(example2);

		final Example example3 = newExample(3L, "Example 3");
		EXAMPLE_SERVICE.create(example3);

		final Example example4 = newExample(4L, "Example 4");
		EXAMPLE_SERVICE.create(example4);

		LOGGER.info(">>>>>> Examples - end");

		return Lists.newArrayList(example1, example2, example3, example4);
	}
}
