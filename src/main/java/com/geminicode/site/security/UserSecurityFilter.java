package com.geminicode.site.security;

import com.geminicode.site.exception.ForbiddenException;
import com.geminicode.site.utils.ResponseHelper;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.apache.log4j.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserSecurityFilter implements ContainerRequestFilter {

	private final static Logger LOGGER = Logger.getLogger(UserSecurityFilter.class.getSimpleName());

	public static final String EXAMPLE_SERVLET = "example";


	@Override
	public ContainerRequest filter(ContainerRequest request) {
		LOGGER.debug("filter");

		try {

			String path = request.getPath();
			String method = request.getMethod();

			LOGGER.info("Path=" + path + ", method=" + method);

			if (path.endsWith("/")) {
				path = path.substring(0, path.length() - 1);
			}

			if (!path.contains(EXAMPLE_SERVLET)) {
				throwForbiddenException();
			}

			return request;

		} catch (ForbiddenException e) {
			LOGGER.error(e.getMessage());
			final Response response = ResponseHelper.respond(Response.Status.FORBIDDEN, e.getMessage());
			throw new WebApplicationException(response);

		}
	}

	private static void throwForbiddenException() throws ForbiddenException {
		throw new ForbiddenException("Forbidden access");
	}
}
