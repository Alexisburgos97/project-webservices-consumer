package com.aburgos.projectwebservicesconsumer.client;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aburgos.projectwebservicesconsumer.dto.EmpleadoDTO;

public class EmpleadoWSClient {
	
	public static void main(String[] args) {
		
		// ::::::::::GET:::::::::
		
		/*Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/project-webservices/aburgos/empleadoWS").path("consultarEmpleadoPorNumeroEmpleado").path("1234");
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		if(response.getStatus() == 204) {
			System.out.println("No se encontro el empleado con el identificatorio número empleado");
		}

		EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);
		
		System.out.println("Nombre del empleado: " + empleadoDTO.getNombre());*/
		
		
		// ::::::::::POST:::::::::
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/project-webservices/aburgos/empleadoWS").path("guardarEmpleado");
		
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		
		empleadoDTO.setNumEmpleado("22");
		empleadoDTO.setNombre("Jose");
		empleadoDTO.setApellido("Perez");
		empleadoDTO.setEdad(39);
		empleadoDTO.setFechaCreacion(LocalDateTime.now());
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(empleadoDTO,MediaType.APPLICATION_JSON));
				
		if(response.getStatus() == 400) {
			String error = response.readEntity(String.class);
			System.out.println(error);
		}

		EmpleadoDTO empleadoDTO2 = response.readEntity(EmpleadoDTO.class);
				
		System.out.println("Nombre del empleado: " + empleadoDTO2.getNombre());
		System.out.println("Fecha de creación: " + empleadoDTO2.getFechaCreacion());
	}
}
