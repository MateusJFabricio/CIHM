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
	
	//Entradas Envase
	public GpioPinDigitalInput inEnvFimDeCursoEnvasadoraEmCima;
	public GpioPinDigitalInput inEnvFimDeCursoEnvasadoraEmbaixo;
	public GpioPinDigitalInput inEnvFimDeCursoEnforcadorAvancado;
	public GpioPinDigitalInput inEnvBombaLigada;
	public GpioPinDigitalInput inEnvFrascoEntrandoNaAreaEnvase;
	public GpioPinDigitalInput inEnvFrascoSaindoDaAreaEnvase;
	public GpioPinDigitalInput inEnvProdutoNivelCritico;
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
		if (gpio == null)
			gpio = GpioFactory.getInstance();
		
		return gpio;
	}
	
	public GPIO()
	{
		getGpio();
		registraGPIOEnvase();
		registraGPIOTampador();
		registraGPIOAcumulador();
	}

	public void registraGPIOEnvase()
	{
		//Saídas
		outEnvBombaEnvase 		= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "outEnvBombaEnvase", PinState.HIGH);
		outEnvEsteira1			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "outEnvEsteira1", PinState.HIGH);
		outEnvEsteira2 			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "outEnvEsteira2", PinState.HIGH);
		outEnvPistaoEnforcador	= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "outEnvPistaoEnforcador", PinState.HIGH);
		outEnvPistaoEnvaseAvanca= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "outEnvPistaoEnvaseAvanca", PinState.HIGH);
		outEnvPistaoEnvaseRecua	= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "outEnvPistaoEnvaseRecua", PinState.HIGH);
		outEnvTrava1			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "outEnvTrava1", PinState.HIGH);
		outEnvTrava2			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "outEnvTrava2", PinState.HIGH);
		
		//Entradas
		inEnvBombaLigada 					= gpio.provisionDigitalInputPin(RaspiPin.GPIO_09,"inEnvBombaLigada", PinPullResistance.PULL_DOWN); 
		inEnvBotaoEmergenciaAcionado 		= gpio.provisionDigitalInputPin(RaspiPin.GPIO_10,"inEnvBotaoEmergenciaAcionado", PinPullResistance.PULL_DOWN);
		inEnvFimDeCursoEnforcadorAvancado 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_11,"inEnvFimDeCursoEnforcadorAvancado", PinPullResistance.PULL_DOWN);
		inEnvFimDeCursoEnvasadoraEmbaixo 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_12,"inEnvFimDeCursoEnvasadoraEmbaixo", PinPullResistance.PULL_DOWN);
		inEnvFimDeCursoEnvasadoraEmCima 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_13,"inEnvFimDeCursoEnvasadoraEmCima", PinPullResistance.PULL_DOWN);
		inEnvFrascoEntrandoNaAreaEnvase 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_14,"inEnvFrascoEntrandoNaAreaEnvase", PinPullResistance.PULL_DOWN);
		inEnvProdutoNivelCritico 			= gpio.provisionDigitalInputPin(RaspiPin.GPIO_15,"inEnvProdutoNivelCritico", PinPullResistance.PULL_DOWN);
				
	}
	
	private void registraGPIOTampador()
	{
		//Saídas
		outTampMotor 					= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "outTampMotor", PinState.HIGH);
		outTampTampador					= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17, "outTampTampador", PinState.HIGH);
		
		//Entradas
		inTampFrascoEmPosicao 			= gpio.provisionDigitalInputPin(RaspiPin.GPIO_18,"inTampFrascoEmPosicao", PinPullResistance.PULL_DOWN);
		inTampFrascoEntrandoCarrossel 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_19,"inTampFrascoEntrandoCarrossel", PinPullResistance.PULL_DOWN);
	}
	
	private void registraGPIOAcumulador()
	{
		//Saídas
		outAcumMotor 			= gpio.provisionDigitalOutputPin(RaspiPin.GPIO_20, "outAcumMotor", PinState.HIGH);
		
		//Entradas
		inAcumFrascoEmPosicao 	= gpio.provisionDigitalInputPin(RaspiPin.GPIO_21,"inAcumFrascoEmPosicao", PinPullResistance.PULL_DOWN);
	}
	
	public void finalize()
	{
		if (!(gpio == null))
			gpio.shutdown();
	}
	
	
	
}
