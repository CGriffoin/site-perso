package com.geminicode.site.initialization;

import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.model.Example;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitServlet extends DataInitServlet {

	private static final long serialVersionUID = 8196970925352625580L;
	private static final Logger LOGGER = Logger.getLogger(InitServlet.class.getSimpleName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String scope = req.getParameter("scope");

		LOGGER.info("doPost -- begin " + scope);
		try {

			if (EXAMPLE.equals(scope)) {
				initExample();

			}

		} catch (Exception e) {
			final String s = "Unable to init database";

			LOGGER.error(s, e);
			throw new ServletException(s, e);
		}

		LOGGER.info("doPost -- end " + scope);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String scope = req.getParameter("scope");

		LOGGER.info("doDelete -- begin " + scope);

		try {

			if (EXAMPLE.equals(scope)) {
				deleteExample();
			}

		} catch (Exception e) {
			final String s = "Unable to clear database";

			LOGGER.error(s, e);
			throw new ServletException(s, e);
		}

		LOGGER.info("doDelete -- end " + scope);
	}

	private void deleteExample() throws NotFoundException, IOException {
		for (final Example example : EXAMPLE_SERVICE.list()) {
			EXAMPLE_SERVICE.delete(example.getId());
		}
	}

	private void initExample() throws Exception {

		final Example example1 = newExample(1L, "Example 1");
		EXAMPLE_SERVICE.create(example1);


		final Example example2 = newExample(2L, "Example 2");
		EXAMPLE_SERVICE.create(example2);


		final Example example3 = newExample(3L, "Example 3");
		EXAMPLE_SERVICE.create(example3);


		final Example example4 = newExample(4L, "Example 4");
		EXAMPLE_SERVICE.create(example4);
	}
}
