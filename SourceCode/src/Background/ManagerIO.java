package Background;

import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import Background.Executor.TaskAcumulador;
import Background.Executor.TaskEmergencia;
import Background.Executor.TaskEnvase;
import Background.Executor.TaskHome;
import Background.Executor.TaskTampador;
import Background.Services.CommAcumulador;
import Background.Services.CommEmergencia;
import Background.Services.CommEnvase;
import Background.Services.CommHome;
import Background.Services.CommTampador;
import Background.Services.GPIO;
import DAO.Produto;

public class ManagerIO {
	//Manager Thread
	private ThreadPoolExecutor pool;
	
	//Threads tarefas
	private TaskEnvase envase;
	private TaskAcumulador acumulador;
	private TaskTampador tampador;
	private TaskHome home;
	private TaskEmergencia emergencia;
	
	//Comunicadores
	private CommAcumulador commAcumulador;
	private CommEnvase commEnvase;
	private CommTampador commTampador;
	private CommHome commHome;
	private CommEmergencia commEmergencia;
	
	//Emergencia
	private Timer timerMonitorEmergencia;
	private ActionListener actEmergencia;
	
	//Obj Comuns
	private GPIO gpio;
	public Produto produto;
	
	public ManagerIO()
	{
		initGPIO();
		initComunicadores();
		initTasks();
		submitThreads();
		iniciaMonitorEmergencia();
	}
	
	private void iniciaMonitorEmergencia() {
		actEmergencia = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (commEmergencia.isEmEmergencia())
				{
					actEmergencia();
				}
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
		commAcumulador 	=  new CommAcumulador();
		commEnvase 		= new CommEnvase();
		commTampador 	= new CommTampador();
		commHome 		= new CommHome();
		commEmergencia 	= new CommEmergencia();
	}
	
	private void initTasks()
	{
		envase 		= new TaskEnvase(commEnvase, gpio);
		acumulador 	= new TaskAcumulador(commAcumulador, gpio);
		tampador 	= new TaskTampador(commTampador, gpio);
		home 		= new TaskHome(commHome, gpio);
		emergencia 	= new TaskEmergencia(commEmergencia, gpio);
	}
	
	private void submitThreads()
	{
		pool = new ThreadPoolExecutor(5, 10, 1, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(10));
		
		pool.submit(envase);
		pool.submit(acumulador);
		pool.submit(tampador);
		pool.submit(home);
		pool.submit(emergencia);
		
		pool.execute(emergencia);
		pool.execute(acumulador);
		pool.execute(envase);
		pool.execute(home);
		pool.execute(tampador);
		
	}
	
	public void iniciarCiclo(Produto produto, boolean frascosPosicionados)
	{
		
	}
	
	public void goHome()
	{
		commHome.setGoGome(true);
	}
	
	private void actEmergencia()
	{
		timerMonitorEmergencia.stop();
		pool.remove(envase);
		pool.remove(acumulador);
		pool.remove(tampador);
		pool.remove(home);
		
		commEmergencia.setGPIOLiberada(true);
	}
	
	public void voltarEmergencia()
	{
		
	}

}
