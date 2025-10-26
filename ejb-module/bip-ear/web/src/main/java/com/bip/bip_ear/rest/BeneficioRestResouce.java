package com.bip.bip_ear.rest;

import java.math.BigDecimal;

import com.bip.bip_ear.TransferenciaServiceEJB;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/beneficio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeneficioRestResouce {

    @Inject
    private TransferenciaServiceEJB service;

    @POST
    @Path("/transferir")
    public Response transferir(@QueryParam("from")Long idFrom, @QueryParam("to")Long idTo, @QueryParam("amount")BigDecimal amount) {
    		service.transfer(idFrom, idTo, amount);
    	return Response.ok("Transferencia Realizada").build();
    }
}