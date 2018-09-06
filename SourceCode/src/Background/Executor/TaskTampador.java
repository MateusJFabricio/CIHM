package Background.Executor;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import Background.Services.CommTampador;
import Background.Services.GPIO;

public class TaskTampador implements Runnable {
	private GPIO gpio;
	private CommTampador comm;
	private Timer timer;
	private ActionListener action;
	protected boolean frascoEmPosicao;
	private int cicloMinimo;
	
	public TaskTampador(CommTampador comm, GPIO gpio)
	{
		this.comm = comm;
		this.gpio = gpio;
		
		action = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (comm.getFrascosNoCarrossel() != 0)
				{
					timer.stop();
					initCicloTampamento();
				}
		}};
		
		timer = new Timer(100, action);
		timer.start();
	}

	protected void initCicloTampamento() {
		while(comm.getFrascosNoCarrossel() != 0)
		{	
			if (gpio.inTampFrascoEmPosicao.isHigh())
			{
				tampar();
				comm.setFrascosTampados(comm.getFrascosTampados() + 1);
				cicloMinimo = 3;
			}
			
			girarCarrossel();
		}
		
		timer.start();
	}
	
	private void girarCarrossel() {
		gpio.outTampMotor.high();
		aguardar(comm.getTempoGiroCarrossel() * 1000);
		gpio.outTampMotor.low();
		aguardar(100);
		cicloMinimo--;
		if (cicloMinimo <= 0)
		{
			comm.setFrascosNoCarrossel(0);
		}
	}

	private void tampar() {
		gpio.outTampTampador.high();
		aguardar(1000);
		gpio.outTampTampador.low();
		aguardar(500);
	}

	@Override
	public void run() {
		ciclo();
	}

	private void ciclo() {
		adicionaListenerFrascoNoCarrossel();
	}

	private void adicionaListenerFrascoNoCarrossel() {
		gpio.inTampFrascoEntrandoCarrossel.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            	comm.setFrascosNoCarrossel(comm.getFrascosNoCarrossel() + 1);
            	cicloMinimo = 10;
            }

    	});
	}
	
	private void aguardar(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
