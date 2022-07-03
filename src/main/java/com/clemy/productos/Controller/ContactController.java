package com.clemy.productos.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clemy.productos.Repository.UsuarioRepository;
import com.clemy.productos.modelo.Usuario;
import com.clemy.productos.servicios.EnvioEmail;


@Controller
public class ContactController {



	@Autowired
	private UsuarioRepository usuarioRepositorio;

	
	@Autowired
	private EnvioEmail envioEmail;



	//////////////////////////////// ENVIO DE CORREOS
	//////////////////////////////// MASIVOS CLIENTES/////////////////////////////////////////////////

	@GetMapping("/contact")
	public String showContactClientsForm() {

		return "Admin/formularioContacto";
	}

	@PostMapping("/contact")
	public String submitContactC(HttpServletRequest request, RedirectAttributes redirectAttrs,
			@RequestParam("attachment") MultipartFile multipartFile)
			throws MessagingException, UnsupportedEncodingException {
	

		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		List<Usuario> listUsers = usuarioRepositorio.findAll();

		String mailContent = "<p><b>Nombre del remitente: </b>" + fullname + "</p>";
		mailContent += "<p><b>Correo de contacto: </b>" + email + "</p>";
		mailContent += "<p><b>Asunto: </b>" + subject + "</p>";
		mailContent += "<p><b>Mensaje: </b>" + content + "</p>";



		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		try {

			for (Usuario u : listUsers) {

				try {
					envioEmail.sendEmail(subject, mailContent, u. getEmail(), fileName, multipartFile);

				} catch (Exception e) {
					redirectAttrs.addFlashAttribute("mensaje", "No se pudo enviar los correos")
							.addFlashAttribute("clase", "danger");
					return "redirect:/contact";
				}

			}

			redirectAttrs.addFlashAttribute("mensaje", "Los correos se enviaron exitosamente")
					.addFlashAttribute("clase", "success");
			return "redirect:/contact";

		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", "No se pudo enviar todos los mensajes")
					.addFlashAttribute("clase", "danger");
			return "redirect:/contact";
		}
	}

}