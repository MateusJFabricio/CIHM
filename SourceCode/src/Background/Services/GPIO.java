package Background.Services;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class GPIO {
	private GpioController gpio;

	//Saídas Envase
	public GpioPinDigitalOutput outEnvBombaEnvase;
	public GpioPinDigitalOutput outEnvPistaoEnvaseAvanca;
	public GpioPinDigitalOutput outEnvPistaoEnvaseRecua;
	public GpioPinDigitalOutput outEnvPistaoEnforcador;
	public GpioPinDigitalOutput outEnvEsteira1;
	public GpioPinDigitalOutput outEnvEsteira2;
	public GpioPinDigitalOutput outEnvTrava1;
	public GpioPinDigitalOutput outEnvTrava2;
	public GpioPinDigitalOutput outEnvBuzzerNivel;
	
	//Entradas Envase
	public GpioPinDigitalInput inEnvFimDeCursoEnvasadoraEmCima;
	public GpioPinDigitalInput inEnvFimDeCursoEnvasadoraEmbaixo;
	public GpioPinDigitalInput inEnvFimDeCursoEnforcadorAvancado;
	public GpioPinDigitalInput inEnvBombaLigada;
	public GpioPinDigitalInput inEnvFrascoEntrandoNaAreaEnvase;
	public GpioPinDigitalInput inEnvFrascoSaindoDaAreaEnvase;
	public GpioPinDigitalInput inEnvProdutoNivelCriticoBaixo;
	public GpioPinDigitalInput inEnvProdutoNivelCriticoAlto;
	public GpioPinDigitalInput inEnvBotaoEmergenciaAcionado;
	
	//Saídas Tampador
	public GpioPinDigitalOutput outTampMotor;
	public GpioPinDigitalOutput outTampTampador;
	
	//Entradas Tampador
	public GpioPinDigitalInput inTampFrascoEntrandoCarrossel;
	public GpioPinDigitalInput inTampFrascoEmPosicao;
	
	//Saídas Acumulador
	public GpioPinDigitalOutput outAcumMotor;
	
	//Entradas Acumulador
	public GpioPinDigitalInput inAcumFrascoEmPosicao;
	
	public GpioController getGpio() {
	
		try {
			if (gpio == null)
				gpio = GpioFactory.getInstance();
			
		} catch (Exception e) {
			System.out.println("Mensagem de erro: " + e.getMessage());
		} catch (UnsatisfiedLinkError e) {
			System.out.println("Mensagem de erro: " + e.getMessage());
			
		}
		return gpio;
	}

	public GPIO()
	{
		getGpio();
		registraGPIOEnvase(gpio);
		registraGPIOTampador(gpio);
		registraGPIOAcumulador(gpio);
	}

	public void registraGPIOEnvase(GpioController gpio)
	{
		//Saídas
		outEnvBombaEnvase 		= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "outEnvBombaEnvase", PinState.HIGH);
		outEnvEsteira1			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "outEnvEsteira1", PinState.HIGH);
		outEnvEsteira2 			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "outEnvEsteira2", PinState.HIGH);
		outEnvPistaoEnforcador	= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "outEnvPistaoEnforcador", PinState.HIGH);
		outEnvPistaoEnvaseAvanca= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "outEnvPistaoEnvaseAvanca", PinState.HIGH);
		outEnvPistaoEnvaseRecua	= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "outEnvPistaoEnvaseRecua", PinState.HIGH);
		outEnvTrava1			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "outEnvTrava1", PinState.HIGH);
		outEnvTrava2			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "outEnvTrava2", PinState.HIGH);
		outEnvBuzzerNivel		= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "outEnvBuzzerNivel", PinState.HIGH);
		
		//Entradas
		inEnvBombaLigada 					= gpio.provisionDigitalInputPin(RaspiPin.GPIO_11,"inEnvBombaLigada", PinPullResistance.PULL_DOWN); 
		inEnvBotaoEmergenciaAcionado 		= gpio.provisionDigitalInputPin(RaspiPin.GPIO_12,"inEnvBotaoEmergenciaAcionado", PinPullResistance.PULL_DOWN);
		inEnvFrascoSaindoDaAreaEnvase 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_13,"inEnvFimDeCursoEnforcadorAvancado", PinPullResistance.PULL_DOWN);
		inEnvFimDeCursoEnvasadoraEmbaixo 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_14,"inEnvFimDeCursoEnvasadoraEmbaixo", PinPullResistance.PULL_DOWN);
		inEnvFimDeCursoEnvasadoraEmCima 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_15,"inEnvFimDeCursoEnvasadoraEmCima", PinPullResistance.PULL_DOWN);
		inEnvFrascoEntrandoNaAreaEnvase 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_16,"inEnvFrascoEntrandoNaAreaEnvase", PinPullResistance.PULL_DOWN);
		inEnvProdutoNivelCriticoBaixo 			= gpio.provisionDigitalInputPin(RaspiPin.GPIO_25,"inEnvProdutoNivelCritico", PinPullResistance.PULL_DOWN);
		inEnvProdutoNivelCriticoAlto 			= gpio.provisionDigitalInputPin(RaspiPin.GPIO_27,"inEnvProdutoNivelCritico", PinPullResistance.PULL_DOWN);
		
	}
	
	private void registraGPIOTampador(GpioController gpio)
	{
		//Saídas
		outTampMotor 					= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "outTampMotor", PinState.HIGH);
		outTampTampador					= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "outTampTampador", PinState.HIGH);
		
		//Entradas
		inTampFrascoEmPosicao 			= gpio.provisionDigitalInputPin(RaspiPin.GPIO_31,"inTampFrascoEmPosicao", PinPullResistance.PULL_DOWN);
		inTampFrascoEntrandoCarrossel 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_21,"inTampFrascoEntrandoCarrossel", PinPullResistance.PULL_DOWN);
	}
	
	private void registraGPIOAcumulador(GpioController gpio)
	{
		//Saídas
		outAcumMotor 			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "outAcumMotor", PinState.HIGH);
		
		//Entradas
		inAcumFrascoEmPosicao 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_23,"inAcumFrascoEmPosicao", PinPullResistance.PULL_DOWN);
	}
	
	protected void finalize()
	{
		if (!(gpio == null))
			gpio.shutdown();
	}

}
