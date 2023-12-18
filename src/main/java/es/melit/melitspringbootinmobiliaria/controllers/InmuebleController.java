package es.melit.melitspringbootinmobiliaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.melit.melitspringbootinmobiliaria.bussiness.ClienteService;
import es.melit.melitspringbootinmobiliaria.bussiness.InmuebleService;
import es.melit.melitspringbootinmobiliaria.dto.InmuebleDto;
import es.melit.melitspringbootinmobiliaria.entities.Cliente;
import es.melit.melitspringbootinmobiliaria.entities.Inmueble;

@RestController

@RequestMapping(path = "/inmuebles")
public class InmuebleController {
	
	private InmuebleService inmuebleService;
	private ClienteService gestionClientes;
	
	@Autowired
	public InmuebleController(InmuebleService inmuebleService, ClienteService gestionClientes) {
		this.inmuebleService = inmuebleService;
		this.gestionClientes = gestionClientes;

	}	
	

	
	@GetMapping
	public List<Inmueble> getInmuebles(){

		 return inmuebleService.listadoInmuebles();
	 }	
	
	@PostMapping(consumes = "application/json")
	public void registerInmueble(@RequestBody InmuebleDto inmuebleDto) {
		Cliente cliente = gestionClientes.conseguirCliente(inmuebleDto.getIdCliente());
		
		Inmueble inmuebleDao = new Inmueble();
		
		inmuebleDao.setActivo(inmuebleDto.isActivo());
		inmuebleDao.setCliente(cliente);
		inmuebleDao.setDescripcion(inmuebleDto.getDescripcion());
		inmuebleDao.setDireccion(inmuebleDto.getDireccion());
		inmuebleDao.setLocalidad(inmuebleDto.getLocalidad());
		inmuebleDao.setNumHabitaciones(inmuebleDto.getNumHabitaciones());
		inmuebleDao.setTipoVivienda(inmuebleDto.getTipoVivienda());
				
		inmuebleService.guardarInmueble(inmuebleDao);
		
	 }
	
	@GetMapping(path = "{idInmueble}")
	public Inmueble getInmueble(@PathVariable("idInmueble") Integer idInmueble){
		 return inmuebleService.bucarInmueble(idInmueble);
	 }

	@PutMapping(consumes = "application/json")
	public void changeSeries(@RequestBody Inmueble inmueble) {		
		inmuebleService.actualizarInmueble(inmueble);	
	}	
	

}
