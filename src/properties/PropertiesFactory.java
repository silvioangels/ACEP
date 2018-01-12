package properties;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesFactory {
	
	public String recuperarPropriedadeMensagens(String lingua, String propriedade) throws Exception{
		
		Properties props = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/messages_"+lingua+".properties");
		props.load(in);
		return props.getProperty(propriedade);
		
	}
	
	public String recuperarPropriedadeBanco(String propriedade) throws Exception{
		
		Properties props = new Properties();
		InputStream in = this.getClass().getResourceAsStream("banco.properties");
		props.load(in);
		return props.getProperty(propriedade);
		
	}

}
