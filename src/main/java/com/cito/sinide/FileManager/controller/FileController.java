package com.cito.sinide.FileManager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cito.sinide.FileManager.entity.RegistroFS;
import com.cito.sinide.FileManager.repository.RegistroFSRepository;

@RestController
@RequestMapping("file")
public class FileController {

	@Autowired
	private RegistroFSRepository registroFSRepository;

	@RequestMapping(value="/{fileID}",method = RequestMethod.GET)
	public void getFile( @PathVariable("fileID") String fileID, HttpServletResponse response){
		try {
			boolean exists = false;
			try{
				exists = registroFSRepository.exists(fileID);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if (exists){
				RegistroFS registro = registroFSRepository.getOne(fileID);
				response.getOutputStream().write(registro.getArchivo());
				response.setStatus(200);
			}else{
				response.getOutputStream().write("Hola Mundo".getBytes());
				response.setStatus(400);
			}
			response.flushBuffer();
		} catch (IOException ex) {
			System.out.println("Houston, acá cito. Cambio.");
			throw new RuntimeException("IOError writing file to output stream");
		}
	}
	
	@RequestMapping(value = "/{fileName}", method = RequestMethod.POST)
	public void authenticationRequest(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response){
		try {
			byte[] bytes = IOUtils.toByteArray(request.getInputStream());
			RegistroFS registro = new RegistroFS();
			registro.setArchivo(bytes);
			registro.setNombre(fileName);
			registro = registroFSRepository.save(registro);
			response.getOutputStream().write(registro.getId().getBytes());
			response.setStatus(200);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setStatus(400);
	}
}
