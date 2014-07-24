package com.geminicode.site.utils;

import javax.ws.rs.core.Response;
import java.util.Map;

public class ResponseHelper {

	public static Response respond(Response.Status status) {

		return Response.status(status).build();
	}

	public static Response respond(Response.Status status, String message) {

		return Response.status(status).entity(message).type("text/plain").build();
	}

	public static Response respond(Response.Status status, Object entity) {

		return Response.status(status).entity(entity).build();
	}

	public static Response respond(Response.Status status, Object entity, String type, Map<String, String> headers) {

		Response.ResponseBuilder builder = Response.status(status).entity(entity).type(type);

		for (final String header : headers.keySet()) {
			builder = builder.header(header, headers.get(header));
		}

		return builder.build();
	}
}
