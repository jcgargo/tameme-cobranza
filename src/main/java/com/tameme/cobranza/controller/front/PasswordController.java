package com.tameme.cobranza.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tameme.cobranza.common.entity.Usuario;
import com.tameme.cobranza.common.service.interfaz.IUsuarioService;

@RestController
public class PasswordController {

	@Autowired
	private IUsuarioService usrService;
	
	//@Autowired
	//private BCryptPasswordEncoder bCrypt;
	
	@PostMapping("/password/confirma")
	public String confirmaCambio(@RequestParam String usuario, @RequestParam String actual, @RequestParam String nueva, @RequestParam String confirma) {
		Usuario usr= usrService.busca(usuario);
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		
		if (usr == null) {
			return "El usuario no existe";
		} else {
			if (!bCrypt.matches(actual, usr.getContrasena()) ) {
				return "La contrase&ntilde;a actual no coincide.";
			} else  {
				if (!nueva.equals(confirma)) {
					return "La contraseña nueva y la confirmación no coinciden.";
				} else {
					usr.setContrasena(bCrypt.encode(nueva));
					usrService.guarda(usr);
				}
			}
		}
		
		return "";
	} 
}
