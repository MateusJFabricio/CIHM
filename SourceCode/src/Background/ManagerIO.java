package Background;

import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import Background.Executor.TaskEmergencia;
import Background.Executor.TaskEnvase;
import Background.Executor.TaskHome;
import Background.Executor.TaskNivel;
import Background.Executor.TaskTampador;
import Background.Services.CommEmergencia;
import Background.Services.CommEnvase;
import Background.Services.CommFrame;
import Background.Services.CommHome;
import Background.Services.CommNivel;
import Background.Services.CommTampador;
import Background.Services.GPIO;
import DAO.Produto;

public class ManagerIO {
	//Manager Thread
	private ThreadPoolExecutor pool;
	
	//Threads tarefas
	private TaskEnvase envase;
	private TaskTampador tampador;
	private TaskHome home;
	private TaskEmergencia emergencia;
	
	//Comunicadores
	private CommEnvase commEnvase;
	private CommTampador commTampador;
	private CommHome commHome;
	private CommEmergencia commEmergencia;
	
	//Emergencia
	private Timer timerMonitorEmergencia;
	private ActionListener actEmergencia;
	
	//Obj Comuns
	public Produto produto;
	public GPIO gpio;

	private CommNivel commNivel;

	private TaskNivel nivel;
	
	public ManagerIO()
	{
		initGPIO();
		initComunicadores();
		initTasks();
		submitThreads();
		iniciaMonitorEmergencia();
		
		goHome();
	}
	
	public void goHome()
	{
		commHome.setGoGome(true);
		
		if (!commHome.isAlive())
			pool.submit(home);
	}
	
	private void iniciaMonitorEmergencia() {
		actEmergencia = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (commEmergencia.isEmEmergencia() && (!commEmergencia.isGPIOLiberada()))
					actEmergencia();
				
				if (!commEmergencia.isEmEmergencia() && (commEmergencia.isGPIOLiberada()))
					voltarEmergencia();
				
		}};
		
		timerMonitorEmergencia = new Timer(10, actEmergencia);
		
		timerMonitorEmergencia.start();
	}

	private void initGPIO()
	{
		gpio = new GPIO();
	}
	
	private void initComunicadores()
	{
		commEnvase 		= new CommEnvase();
		commTampador 	= new CommTampador();
		commHome 		= new CommHome();
		commEmergencia 	= new CommEmergencia();
		commNivel		= new CommNivel();
	}
	
	private void initTasks()
	{
		envase 		= new TaskEnvase(commEnvase, gpio);
		tampador 	= new TaskTampador(commTampador, gpio);
		home 		= new TaskHome(commHome, gpio);
		emergencia 	= new TaskEmergencia(commEmergencia, gpio);
		nivel		= new TaskNivel(commNivel, gpio);
	}
	
	private void submitThreads()
	{
		pool = new ThreadPoolExecutor(5, 10, 1, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(10));
		
		pool.submit(envase);
		pool.submit(tampador);
		pool.submit(home);
		pool.submit(emergencia);
		pool.submit(nivel);
		
	}
	
	public void iniciarCiclo(Produto produto, boolean frascosPosicionados, int meta)
	{
		commEnvase.setCicloContinuo(meta <= 0);
		commEnvase.setIniciaProducao(true);
		commEnvase.setInicioRapido(frascosPosicionados);
		commEnvase.setTempoEnvase(produto.getTempoEnvase());
		if (commEnvase.isAlive())
		{
			commEnvase.setMetaProducao(commEnvase.getMetaProducao() + meta);
		}else
		{
			commEnvase.setMetaProducao(meta);
			pool.submit(envase);
		}
		
		
		commTampador.setIniciaProducao(true);
		if (!commTampador.isAlive())
			pool.submit(tampador);
		
		gpio.outAcumMotor.low();
	}
	
	private void actEmergencia()
	{
		envase.gpio = null;
		tampador.gpio = null;
		home.gpio = null;

		commEmergencia.setGPIOLiberada(true);
		
		commTampador.setIniciaProducao(false);
		gpio.outAcumMotor.high();
	}
	
	public void voltarEmergencia()
	{
		commEmergencia.setGPIOLiberada(false);
		gpio.outAcumMotor.high();
		envase.gpio = gpio;
		tampador.gpio = gpio;
		home.gpio = gpio;
		
		goHome();
	}

	public void interromperCiclo() {
		commEnvase.setIniciaProducao(false);
		commTampador.setIniciaProducao(false);
		gpio.outAcumMotor.high();
	}

	public void atualizarDadosFrame(CommFrame comunicador) {
		comunicador.setEmergencia(commEmergencia.isEmEmergencia());
		comunicador.setProduzido(commEnvase.getMetaProducao());
		comunicador.setNivelAlto(commNivel.isNivelAlto());
		comunicador.setNivelBaixo(commNivel.isNivelBaixo());
	}

}
