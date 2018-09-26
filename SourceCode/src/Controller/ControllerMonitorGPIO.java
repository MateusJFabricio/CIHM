package Controller;

public class ControllerMonitorGPIO {
	
	public void interromperAcionamentos()
	{
		
	}
	
	public String buscarEstadoOutput(int numOut)
	{
		switch (numOut) {
		case 1:
			if (Main.Main.mannIO.gpio.outEnvBombaEnvase.isHigh())
				return "ON";
			else
				return "OFF";
			
		case 2:
			if (Main.Main.mannIO.gpio.outEnvEsteira1.isHigh())
				return "ON";
			else
				return "OFF";
		case 3:
			if (Main.Main.mannIO.gpio.outEnvEsteira2.isHigh())
				return "ON";
			else
				return "OFF";
		case 4:
			if (Main.Main.mannIO.gpio.outEnvPistaoEnforcador.isHigh())
				return "ON";
			else
				return "OFF";
		case 5:
			if (Main.Main.mannIO.gpio.outEnvPistaoEnvaseAvanca.isHigh())
				return "ON";
			else
				return "OFF";
		case 7:
			if (Main.Main.mannIO.gpio.outEnvPistaoEnvaseRecua.isHigh())
				return "ON";
			else
				return "OFF";
		case 8:
			if (Main.Main.mannIO.gpio.outEnvTrava1.isHigh())
				return "ON";
			else
				return "OFF";
		case 10:
			if (Main.Main.mannIO.gpio.outEnvTrava2.isHigh())
				return "ON";
			else
				return "OFF";
		case 26:
			if (Main.Main.mannIO.gpio.outEnvBuzzerNivel.isHigh())
				return "ON";
			else
				return "OFF";
		case 28:
			if (Main.Main.mannIO.gpio.outTampMotor.isHigh())
				return "ON";
			else
				return "OFF";
		case 29:
			if (Main.Main.mannIO.gpio.outTampTampador.isHigh())
				return "ON";
			else
				return "OFF";
		case 22:
			if (Main.Main.mannIO.gpio.outAcumMotor.isHigh())
				return "ON";
			else
				return "OFF";
		default:
			break;
		}
		return "erro";
	}
	
	public String buscarEstadoInput(int numIn)
	{
		switch (numIn) {
		case 11:
			if (Main.Main.mannIO.gpio.inEnvBombaLigada.isHigh())
				return "ON";
			else
				return "OFF";
			
		case 12:
			if (Main.Main.mannIO.gpio.inEnvBotaoEmergenciaAcionado.isHigh())
				return "ON";
			else
				return "OFF";
		case 13:
			if (Main.Main.mannIO.gpio.inEnvFrascoSaindoDaAreaEnvase.isHigh())
				return "ON";
			else
				return "OFF";
		case 14:
			if (Main.Main.mannIO.gpio.inEnvFimDeCursoEnvasadoraEmbaixo.isHigh())
				return "ON";
			else
				return "OFF";
		case 15:
			if (Main.Main.mannIO.gpio.inEnvFimDeCursoEnvasadoraEmCima.isHigh())
				return "ON";
			else
				return "OFF";
		case 16:
			if (Main.Main.mannIO.gpio.inEnvFrascoEntrandoNaAreaEnvase.isHigh())
				return "ON";
			else
				return "OFF";
		case 25:
			if (Main.Main.mannIO.gpio.inEnvProdutoNivelCriticoBaixo.isHigh())
				return "ON";
			else
				return "OFF";
		case 27:
			if (Main.Main.mannIO.gpio.inEnvProdutoNivelCriticoAlto.isHigh())
				return "ON";
			else
				return "OFF";
		case 31:
			if (Main.Main.mannIO.gpio.inTampFrascoEmPosicao.isHigh())
				return "ON";
			else
				return "OFF";
		case 21:
			if (Main.Main.mannIO.gpio.inTampFrascoEntrandoCarrossel.isHigh())
				return "ON";
			else
				return "OFF";
		case 23:
			if (Main.Main.mannIO.gpio.inAcumFrascoEmPosicao.isHigh())
				return "ON";
			else
				return "OFF";
		default:
			break;
		}
		return "erro";
	}
	
	public void mudarEstadoIO(boolean acionar, int numSaida)
	{
		if (acionar)
		{
			switch (numSaida) {
			case 1:
				Main.Main.mannIO.gpio.outEnvBombaEnvase.high();
				break;
			case 2:
				Main.Main.mannIO.gpio.outEnvEsteira1.high();
				break;
			case 3:
				Main.Main.mannIO.gpio.outEnvEsteira2.high();
				break;
			case 4:
				Main.Main.mannIO.gpio.outEnvPistaoEnforcador.high();
				break;
			case 5:
				Main.Main.mannIO.gpio.outEnvPistaoEnvaseAvanca.high();
				break;
			case 7:
				Main.Main.mannIO.gpio.outEnvPistaoEnvaseRecua.high();
				break;
			case 8:
				Main.Main.mannIO.gpio.outEnvTrava1.high();
				break;
			case 10:
				Main.Main.mannIO.gpio.outEnvTrava2.high();
				break;
			case 26:
				Main.Main.mannIO.gpio.outEnvBuzzerNivel.high();
				break;
			case 28:
				Main.Main.mannIO.gpio.outTampMotor.high();
				break;
			case 29:
				Main.Main.mannIO.gpio.outTampTampador.high();
				break;
			case 22:
				Main.Main.mannIO.gpio.outAcumMotor.high();
				break;
			default:
				break;
			}
		}else
		{
			switch (numSaida) {
			case 1:
				Main.Main.mannIO.gpio.outEnvBombaEnvase.low();
				break;
			case 2:
				Main.Main.mannIO.gpio.outEnvEsteira1.low();
				break;
			case 3:
				Main.Main.mannIO.gpio.outEnvEsteira2.low();
				break;
			case 4:
				Main.Main.mannIO.gpio.outEnvPistaoEnforcador.low();
				break;
			case 5:
				Main.Main.mannIO.gpio.outEnvPistaoEnvaseAvanca.low();
				break;
			case 7:
				Main.Main.mannIO.gpio.outEnvPistaoEnvaseRecua.low();
				break;
			case 8:
				Main.Main.mannIO.gpio.outEnvTrava1.low();
				break;
			case 10:
				Main.Main.mannIO.gpio.outEnvTrava2.low();
				break;
			case 26:
				Main.Main.mannIO.gpio.outEnvBuzzerNivel.low();
				break;
			case 28:
				Main.Main.mannIO.gpio.outTampMotor.low();
				break;
			case 29:
				Main.Main.mannIO.gpio.outTampTampador.low();
				break;
			case 22:
				Main.Main.mannIO.gpio.outAcumMotor.low();
				break;
			default:
				break;
			}
		}
	}
}
