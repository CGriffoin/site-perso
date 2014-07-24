package com.geminicode.site.facade;

import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.model.dto.ExampleDto;
import com.geminicode.site.model.dto.ExampleListDto;
import com.geminicode.site.serviceDto.ExampleServiceDto;
import com.geminicode.site.serviceDto.ServiceDtoSingleton;
import com.geminicode.site.utils.ResponseHelper;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/example")
@Produces(MediaType.APPLICATION_JSON)
public class ExampleRestServlet {

	private final static Logger LOGGER = Logger.getLogger(ExampleRestServlet.class.getSimpleName());

	private final static ExampleServiceDto EXAMPLE_SERVICE = ServiceDtoSingleton.getExampleServiceDto();

	@GET
	public Response list() {
		LOGGER.info("Examples list");

		try {
			final List<ExampleListDto> exampleListDtos = EXAMPLE_SERVICE.list();
			return ResponseHelper.respond(Response.Status.OK, exampleListDtos);

		} catch (final IOException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") final Long id) {
		LOGGER.info("Example get : id=" + id);

		try {
			final ExampleDto exampleDto = EXAMPLE_SERVICE.get(id);
			return ResponseHelper.respond(Response.Status.OK, exampleDto);

		} catch (NotFoundException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.NOT_FOUND, e.getMessage());

		} catch (final IOException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@POST
	@Path("/name")
	public Response getByName(final String name) {
		LOGGER.info("Example get by name : name=" + name);

		try {
			final ExampleDto exampleDto = EXAMPLE_SERVICE.getByName(name);
			return ResponseHelper.respond(Response.Status.OK, exampleDto);

		} catch (NotFoundException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.NOT_FOUND, e.getMessage());

		} catch (final IOException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@POST
	public Response insert(final ExampleDto exampleDto) {
		LOGGER.info("Example insert : " + exampleDto.toString());

		try {
			EXAMPLE_SERVICE.create(exampleDto);
			return ResponseHelper.respond(Response.Status.OK, exampleDto);

		} catch (final IOException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") final Long id,
						   ExampleDto exampleDto) {
		LOGGER.info("Example update : id=" + id + ", " + exampleDto.toString());

		try {
			EXAMPLE_SERVICE.update(exampleDto, id);
			return ResponseHelper.respond(Response.Status.OK, exampleDto);

		} catch (NotFoundException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.NOT_FOUND, e.getMessage());

		} catch (final IOException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") final Long id) {
		LOGGER.info("Example delete : id=" + id);

		try {
			EXAMPLE_SERVICE.delete(id);
			return ResponseHelper.respond(Response.Status.OK);

		} catch (NotFoundException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.NOT_FOUND, e.getMessage());

		} catch (final IOException e) {
			LOGGER.error(e);
			return ResponseHelper.respond(Response.Status.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}

	}
}
