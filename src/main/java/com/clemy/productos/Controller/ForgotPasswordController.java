package com.clemy.productos.Controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.clemy.productos.daoImpl.UsuarioImp;
import com.clemy.productos.daoImpl.UsuarioNotFoundException;
import com.clemy.productos.modelo.Usuario;

import net.bytebuddy.utility.RandomString;
 
@Controller
public class ForgotPasswordController {
	
	@Autowired
	private UsuarioImp usuarioImp;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/forgot_password")
	public String ShowForgotPasswordForm(Model model) {
		model.addAttribute("PageTitle","recuperar contraseña");
		return "forgot_password_form";
	}
	
	@PostMapping("/forgot_password")
	public String  processForgotPasswordForm(HttpServletRequest request,
			Model model) throws UnsupportedEncodingException, MessagingException {
		String email = request.getParameter("email");
		String token = RandomString.make(45);
		
		try { 
		 usuarioImp.updateResetPasswordToken(token, email);
			
		 String resetPasswordLink = UsuarioImp.getSiteURL(request)+ "/reset_password?token="+token;
		 sendEmail(email,resetPasswordLink);
		 
		 model.addAttribute("message", "Hemos enviado un link de recupearar contraseña a su correo: " + email);
		
		} catch (UsuarioNotFoundException ex) {
			
			model.addAttribute("error", ex.getMessage());
		}catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error al enviar el correo");
		}
		
		return "forgot_password_form";
		 
	}

	private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
	 MimeMessage message= mailSender.createMimeMessage();
	 MimeMessageHelper helper = new  MimeMessageHelper(message);
		
	 helper.setFrom("productosclemy@gmail.com", "ClemySupport");
	 helper.setTo(email);
	 
	 String subject ="AQUII!!, Recupera tu contraseña";
	 String content = "<p>Holaa!,</p>"+"Estimado(a) Usuario, hemos recuperado tu contraseña, intenta ingresar al siguiente link para restablecer su contraseña</p>"
			+ "<p><b><a href=\""+ resetPasswordLink +"\">Cambiar mi contraseña</a><b></p>"
			+"<p>Le recordamos que esta dirección de e-mail es utilizada solamente para los envíos de la información solicitada. Por favor no responda con consultas personales ya que no podrán ser respondidas.</p>"
	 		+ "<p>Cordialmente.</p>"
	 		+ "<p>Productos Clemy - SST.</p>";
	 		
	 helper.setSubject(subject);
	 helper.setText(content,true);
	 
	 mailSender.send(message);
	 
	}
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
	    Usuario usuario = usuarioImp.get(token);
	     
	    if (usuario == null) {
	        model.addAttribute("title", "Restablecer Contraseña");
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    }
	     model.addAttribute("token", token);
	     model.addAttribute("PageTitle","recuperar contraseña");
	     
	    return "reset_password_form";
	}
	
 @PostMapping("/reset_password")
 public String processResetPassword(HttpServletRequest request, Model model) {
     String token = request.getParameter("token");
     String password = request.getParameter("password");
      
     Usuario usuario = usuarioImp.get(token);
     model.addAttribute("title", "Se restablacio la contraseña");
      
     if (usuario== null) {
         model.addAttribute("title", "Restablecer contraseña");
         model.addAttribute("message", "Invalid Token");
         return "message";
     } else {           
    	 usuarioImp.updatePassword(usuario, password);
          
         model.addAttribute("message", "Cambio la contraseña con exito!.");
     }
      
     return "message";
 }
}
