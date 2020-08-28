package db;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;

import java.util.List;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.quarkus.panache.common.Sort;

/*
import java.io.FileWriter;
import java.io.IOException;
*/

@Path("/employees")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyService {

	@GET
	public List<Employee> get(){
		// System.out.println("Changes were compiled dynamically");
		return Employee.listAll(Sort.by("name"));
	}
	@GET
	@Path("/{id}")
	public Employee getSingle(@PathParam("id") Long id){// throws IOException{
		/*
		FileWriter w1=new FileWriter("/home/ubuntu/SCart-QUARKUS/hibernate-demo/src/target/logs.txt", true);
		w1.write("ID:"+id);
		w1.close();
		*/
		Employee entity=Employee.findById(id);
		/*
		FileWriter w2=new FileWriter("/home/ubuntu/SCart-QUARKUS/hibernate-demo/src/target/logs2.txt", true);
		w2.write("EMP" + entity.name + " " + entity.department);
		w2.close();
		*/
		if(entity == null){
			throw new WebApplicationException("Ticket: " + id + " does not exist", 404);
		}
		return entity;
	}

	@POST
	@Transactional
	public Response create(Employee emp){
		if(emp.id != null){
			throw new WebApplicationException("Ticket ID was invalidly set", 422);
		}
		emp.persist();
		return Response.ok(emp).status(201).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Employee update(@PathParam("id") Long id, Employee emp){
		if (emp.name== null){
			throw new WebApplicationException("Ticet naem was not set", 422);
		}
		Employee entity=Employee.findById(id);
		if(entity == null){
			throw new WebApplicationException("Ticket: " + id + " does not exist", 404);
		}
		entity.workExp=emp.workExp;
		if (entity.department.equals(emp.department) == false){
			System.out.println("Experience reset to zero, department updated");
			entity.workExp=0;
			entity.department=emp.department;
		}
		return entity;
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response delete(@PathParam("id") Long id){
		Employee entity = Employee.findById(id);
		if(entity == null){
			throw new WebApplicationException("Ticket: " + id + " does not exist", 404);
		}
		entity.delete();
		return Response.status(204).build();
	}

	@Provider
	public static class ErrorMapper implements ExceptionMapper<Exception>{
		@Override
		public Response toResponse(Exception e){
			int code = 500;
			if (e instanceof WebApplicationException){
				code = ((WebApplicationException) e).getResponse().getStatus();
			}
			return Response.status(code)
					.entity(Json.createObjectBuilder().add("error", e.getMessage())
									  .add("code", code)
									  .build()
					       ).build();
		}
	}
}
