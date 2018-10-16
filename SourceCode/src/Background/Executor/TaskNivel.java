package Background.Executor;

import javax.swing.JOptionPane;
import Background.Services.CommNivel;
import Background.Services.GPIO;

public class TaskNivel implements Runnable {
	private CommNivel comm;
	private GPIO gpio;
	
	public TaskNivel(CommNivel comm, GPIO gpio)
	{
		this.gpio = gpio;
		this.comm = comm;
		gpio.outEnvBuzzerNivel.high();
	}


	@Override
	public void run() {
		while(true)
		{
			/*
			if (gpio.inEnvProdutoNivelCriticoBaixo.isHigh() && !comm.isNivelBaixo())
			{
				aguardar(2000);
				if (gpio.inEnvProdutoNivelCriticoBaixo.isLow())
					continue;
				comm.setNivelBaixo(true);
				gpio.outEnvBuzzerNivel.low();
				JOptionPane.showMessageDialog(null, "Reservatório com nivel baixo. Abasteça o reservatório");
				gpio.outEnvBuzzerNivel.high();
			}else if (gpio.inEnvProdutoNivelCriticoBaixo.isLow())
			{
				aguardar(2000);
				if (gpio.inEnvProdutoNivelCriticoBaixo.isHigh())
					continue;
				comm.setNivelBaixo(false);
			}else if (gpio.inEnvProdutoNivelCriticoAlto.isHigh() && !comm.isNivelAlto())
			{
				aguardar(2000);
				if (gpio.inEnvProdutoNivelCriticoAlto.isLow())
					continue;
				comm.setNivelAlto(true);
				gpio.outEnvBuzzerNivel.low();
				JOptionPane.showMessageDialog(null, "Reservatório abastecido. Interrompa o abastecimento");
				gpio.outEnvBuzzerNivel.high();
			}else if (gpio.inEnvProdutoNivelCriticoAlto.isLow())
			{
				aguardar(2000);
				if (gpio.inEnvProdutoNivelCriticoAlto.isHigh())
					continue;
				comm.setNivelAlto(false);
			}
				*/
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
