package Background.Services;

public class CommHome {
	private boolean goGome = false;
	private boolean finalizado = true;
	private boolean Alive = false;
	
	public synchronized void setGoGome(boolean goGome) {
		this.goGome = goGome;
		notifyAll();
	}

	public synchronized boolean isGoHome()
	{
		return goGome;
	}
	
	public synchronized boolean isFinalizado() {
		return finalizado;
	}

	public synchronized void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
		notifyAll();
	}

	public boolean isAlive() {
		return Alive;
	}

	public void setAlive(boolean alive) {
		Alive = alive;
	}
	
	
}
