package maquina1995.spring.basico.main;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import maquina1995.spring.basico.configuration.JavaConfig;
import maquina1995.spring.basico.dominio.Espadachin;
import maquina1995.spring.basico.dominio.Mago;

/**
 * Main que explica el uso de beans por java config
 * <p>
 * Para coger 1 bean del contexto del contenedor de spring desde una clase
 * estática nunca podrás usar el @Autowired o @Resource deberás instanciar el
 * contexto y obtener a traves de los distintos métodos disponibles:
 * <p>
 * <li>Equivalente a @Resource
 * {@link AnnotationConfigApplicationContext#getBean(String,Class)}</li>
 * <p>
 * <li>Equivalente a @Autowired
 * {@link AnnotationConfigApplicationContext#getBean(Class)}</li>
 * <p>
 * <li>O bien obtenerle por ID:
 * {@link AnnotationConfigApplicationContext#getBean(String)}</li>
 * <p>
 * 
 * @author MaQuiNa1995
 *
 */
public class MainJavaConfig {

    /**
     * Logger genérico de la clase
     */
    private static final Logger LOGGER = Logger.getLogger(MainJavaConfig.class.getName());

    public static void main(String[] args) {

	LOGGER.info("Inicia la aplicación");

	// Try con recursos para cerrar automaticamente al final del try el contexto
	try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class)) {

	    String nombreBeanEspadachin = "espadachin";

	    // ------------------------ Singleton ------------------------
	    LOGGER.info("----- Singleton -----");

	    // Recordemos que se han dado valores por defecto a la clase {@link Espadachin}
	    // al recorgerlo del contenedor de spring vendrán con esos campos seteados
	    Espadachin espadachinSingleton = context.getBean(nombreBeanEspadachin, Espadachin.class);
	    Espadachin espadachinSingleton2 = context.getBean(nombreBeanEspadachin, Espadachin.class);

	    // Como se puede ver se le setea tanto al bean 1 como al 2 uno de sus atributos
	    espadachinSingleton2.setArma("ArmaCambiada");
	    espadachinSingleton.setDanno(999999);

	    // Ahora vemos que pasa
	    LOGGER.info("\nEspadachin Singleton: " + espadachinSingleton + "\nEspadachin Singleton 2: "
		    + espadachinSingleton2 + "\n");

	    String nombreBeanMago = "mago";

	    // ------------------------ Prototype ------------------------
	    LOGGER.info("----- Prototype -----");

	    // Recordemos que se han dado valores por defecto a la clase {@link Mago}
	    Mago magoPrototype = context.getBean(nombreBeanMago, Mago.class);
	    Mago magoPrototype2 = context.getBean(nombreBeanMago, Mago.class);

	    // Como se puede ver se le setea al bean 1 sus atributos pero en este caso no
	    // tienen el mismo valor los 2
	    magoPrototype.setArma("ArmaCambiada");
	    magoPrototype.setDanno(999999);

	    // Lo vemos aqui
	    LOGGER.info("\nMago Singleton: " + magoPrototype + "\nMago Singleton 2: " + magoPrototype2 + "\n");

	}
	LOGGER.info("Termina la aplicación");

    }

}
