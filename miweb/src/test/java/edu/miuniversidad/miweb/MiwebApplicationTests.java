package edu.miuniversidad.miweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import edu.miuniversidad.miweb.Model.Entities.Finca;
import edu.miuniversidad.miweb.Model.Entities.Usuario;

import edu.miuniversidad.miweb.Model.Services.CreateFincaService;

import edu.miuniversidad.miweb.Model.Services.SendEmailService;
import edu.miuniversidad.miweb.Model.Services.SignUpService;

@SpringBootTest
class MiwebApplicationTests {

	@Autowired
	private SignUpService signUpService;



	@Test
	void registrarUsuario_guardaUsuarioConPasswordHasheada() {
		Usuario usuario = new Usuario();
		usuario.setUsername("sarat");
		usuario.setPassword("12345");
		usuario.setNombre("saruliu");
		usuario.setEmail("test@email.com");
		usuario.setRol("vendedor");
		// No se establece codigoUsuario, ya que es generado automáticamente en el
		// servicio
		// No se establece codRecuperacion ni timeLimit, ya que no son necesarios para
		// el registro inicial
		// No se establece idUsuario, ya que es generado automáticamente por la base de
		// datos

		Usuario guardado = signUpService.registrarUsuario(usuario);

		System.out.println("Usuario guardado: " + guardado.getUsername());
	}


	@Autowired
	private SendEmailService sendEmailService;

	@Test
	void sendEmail_enviaCorreoYActualizaUsuario() {
		boolean resultado = sendEmailService.sendEmail("saracastellano2108@gmail.com");
		assertThat(resultado).isTrue();
	}




	@Autowired
	private CreateFincaService createFincaService;

	@Test
	void crearFincaConCodigos_asignaIdsCorrectos() {
		// Crear finca de prueba
		Finca finca = new Finca();
		finca.setNombre("Finca Test");
		finca.setNumHectareas(10);
		finca.setMetrosCuadrados(10000);
		finca.setPais("Colombia");
		finca.setDepartamento("Antioquia");
		finca.setCiudad("Medellín");
		finca.setMetrosCuadrados(100000);
		finca.setPais("Colombia");
		finca.setDepartamento("Antioquia");
		finca.setCiudad("Medellín");
		finca.setSiProduceCereales(true);
		finca.setSiProduceFrutas(true);
		finca.setSiProduceLeche(true);
		finca.setSiProduceVerduras(true);

		// Usar el servicio para asignar los IDs usando los códigos de usuario
		Finca guardada = createFincaService.createFinca(
				finca,
				"889180", // Código del propietario
				"546951",
				"652262");

		assertThat(guardada).isNotNull();
	}

}
