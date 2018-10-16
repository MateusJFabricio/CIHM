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
		aguardar(500);
		gpio.outTampTampador.high();
		aguardar(500);
	}

	@Override
	public void run() {
	
		try {
			comm.setAlive(true);
			gpio.outEnvEsteira2.low();
			while(comm.isIniciaProducao())
			{
				
				while(gpio.inTampFrascoEmPosicao.isLow())
					continue;
				aguardar(100);
				
				if (gpio.inTampFrascoEmPosicao.isLow())
					continue;
				
				tampar();
				
				//Faz o carrossel sair de posicao
				gpio.outTampMotor.low();
				
				if (gpio.inTampFrascoEntrandoCarrossel.isLow())
				{	
					while (gpio.inTampFrascoEntrandoCarrossel.isLow())
						aguardar(10);
				}else
				{
					while (gpio.inTampFrascoEntrandoCarrossel.isHigh())
						aguardar(10);
					
					while (gpio.inTampFrascoEntrandoCarrossel.isLow())
						aguardar(10);
					
				}
				
				gpio.outTampMotor.high();
				

			}
			
			gpio.outEnvEsteira2.high();
			
			
			
		} catch (Exception e) {
			
		}finally {
			comm.setAlive(false);
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
