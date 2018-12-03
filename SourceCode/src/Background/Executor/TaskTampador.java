package Background.Executor;

import Background.Services.CommTampador;
import Background.Services.GPIO;

public class TaskTampador implements Runnable {
	public GPIO gpio;
	private CommTampador comm;
	protected boolean frascoEmPosicao;
	
	public TaskTampador(CommTampador comm, GPIO gpio)
	{
		this.comm = comm;
		this.gpio = gpio;
	}
	
	private void tampar() {
		gpio.outTampTampador.low();
		aguardar(350);
		gpio.outTampTampador.high();
		aguardar(200);
	}

	@Override
	public void run() {
	
		try {
			//comm.setAlive(true);
			gpio.outEnvEsteira2.low();
			gpio.outTampPosicionador.high();
			while(comm.isIniciaProducao())
			{
				
				while(gpio.inTampFrascoEmPosicao.isLow() && comm.isIniciaProducao())
					continue;
				aguardar(30);
				
				if (gpio.inTampFrascoEmPosicao.isLow())
					continue;
				
				//avancar posicionador
				gpio.outTampPosicionador.low();
				aguardar(350);
				
				tampar();
				
				//Faz o carrossel sair de posicao
				gpio.outTampMotor.low();
				
				if (gpio.inTampFrascoEntrandoCarrossel.isLow())
				{	
					System.out.println("Passei aqui");
					while (gpio.inTampFrascoEntrandoCarrossel.isLow() && comm.isIniciaProducao())
						aguardar(10);
				}else
				{
					System.out.println("Passei ali");
					aguardaSensorDenteDesacionar();
					aguardaSensorDenteAcionar();
										
				}
				
				gpio.outTampMotor.high();
				
				//recua posicionador
				gpio.outTampPosicionador.high();
				aguardar(350);
			}

			
			
			gpio.outEnvEsteira2.high();
			
				
		} catch (Exception e) {
			comm.setAlive(false);
		}finally {
			comm.setAlive(false);
		}
		
	}

	private void aguardaSensorDenteAcionar() {
		while(comm.isIniciaProducao())
		{
			if (gpio.inTampFrascoEntrandoCarrossel.isHigh())
			{
				aguardar(20);
				if (gpio.inTampFrascoEntrandoCarrossel.isHigh())
				{
					aguardar(20);
					if(gpio.inTampFrascoEntrandoCarrossel.isHigh())
						return;
				}
			}
			
		}
	}

	private void aguardaSensorDenteDesacionar() {
		while(comm.isIniciaProducao())
		{
			if (gpio.inTampFrascoEntrandoCarrossel.isLow())
			{
				aguardar(20);
				if (gpio.inTampFrascoEntrandoCarrossel.isLow())
				{
						aguardar(20);
					if(gpio.inTampFrascoEntrandoCarrossel.isLow())
						return;
				}
			}
		}
	}

	private void aguardar(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
