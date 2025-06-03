package edu.miuniversidad.miweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import edu.miuniversidad.miweb.Model.Entities.Finca;
import edu.miuniversidad.miweb.Model.Entities.Usuario;
import edu.miuniversidad.miweb.Model.Services.ChangePasswordService;
import edu.miuniversidad.miweb.Model.Services.CreateFincaService;
import edu.miuniversidad.miweb.Model.Services.LoginService;
import edu.miuniversidad.miweb.Model.Services.SendEmailService;
import edu.miuniversidad.miweb.Model.Services.SignUpService;

@SpringBootTest
class MiwebApplicationTests {

	@Autowired
	private SignUpService signUpService;

	@Autowired
	private LoginService loginService;

	@Test
	void registrarUsuario_guardaUsuarioConPasswordHasheada() {
		Usuario usuario = new Usuario();
		usuario.setUsername("Lilo");
		usuario.setPassword("12345");
		usuario.setNombre("Javi");
		usuario.setEmail("test@email.com");
		usuario.setRol(Usuario.Rol.vendedor);
		// No se establece codigoUsuario, ya que es generado automáticamente en el
		// servicio
		// No se establece codRecuperacion ni timeLimit, ya que no son necesarios para
		// el registro inicial
		// No se establece idUsuario, ya que es generado automáticamente por la base de
		// datos

		Usuario guardado = signUpService.registrarUsuario(usuario);

		System.out.println("Usuario guardado: " + guardado.getUsername());
	}

	@Test
	void login_usuarioOEmailYPassword() {
		// Primero registramos un usuario de prueba
		Usuario usuario = new Usuario();
		usuario.setUsername("testlogin" + System.currentTimeMillis());
		usuario.setPassword("clave123");
		usuario.setNombre("Nombre Login");
		usuario.setEmail("login" + System.currentTimeMillis() + "@mail.com");
		usuario.setRol(Usuario.Rol.vendedor);

		Usuario guardado = signUpService.registrarUsuario(usuario);

		// Probar login por username
		boolean loginPorUsername = loginService.login(guardado.getUsername(),
				"clave123");
		assertThat(loginPorUsername).isTrue();

		// Probar login por email
		boolean loginPorEmail = loginService.login(guardado.getEmail(), "clave123");
		assertThat(loginPorEmail).isTrue();

		// Probar login fallido
		boolean loginFallido = loginService.login("noexiste", "clave123");
		assertThat(loginFallido).isFalse();
	}

	@Autowired
	private SendEmailService sendEmailService;

	@Test
	void sendEmail_enviaCorreoYActualizaUsuario() {
		boolean resultado = sendEmailService.sendEmail("saracastellano2108@gmail.com");
		assertThat(resultado).isTrue();
	}

	@Autowired
	private ChangePasswordService changePasswordService;

	@Test
	void cambiarPassword_cambiaConCodigoYTiempoCorrecto() {
		// Crear y registrar usuario de prueba
		Usuario usuario = new Usuario();
		usuario.setUsername("testcambio" + System.currentTimeMillis());
		usuario.setPassword("viejaClave");
		usuario.setNombre("Cambio");
		usuario.setEmail("cambio" + System.currentTimeMillis() + "@mail.com");
		usuario.setRol(Usuario.Rol.vendedor);
		Usuario guardado = signUpService.registrarUsuario(usuario);

		// Simular envío de código de recuperación
		int codigo = 123456;
		guardado.setCodRecuperacion(codigo);
		guardado.setTimeLimit(java.time.LocalDateTime.now().plusMinutes(15));
		// Guardar cambios en la base de datos
		signUpService.registrarUsuario(guardado);

		// Intentar cambiar la contraseña con el código correcto y dentro del tiempo
		boolean resultado = changePasswordService.cambiarPassword(
				guardado.getEmail(), codigo, "nuevaClaveSegura");
		assertThat(resultado).isTrue();

		// Verificar que la contraseña realmente cambió (usando login)
		boolean loginConNueva = loginService.login(guardado.getEmail(), "nuevaClaveSegura");
		assertThat(loginConNueva).isTrue();

		// Intentar cambiar la contraseña con código incorrecto
		boolean resultadoFallo = changePasswordService.cambiarPassword(
				guardado.getEmail(), 999999, "otraClave");
		assertThat(resultadoFallo).isFalse();
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
