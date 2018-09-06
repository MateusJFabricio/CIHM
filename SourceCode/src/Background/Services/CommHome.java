package Background.Services;

public class CommHome {
	private boolean goGome = false;
	private boolean finalizado = true;
	private String statusAtual = "";
	
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

	public synchronized String getStatusAtual() {
		return statusAtual;
	}

	public synchronized void setStatusAtual(String statusAtual) {
		this.statusAtual = statusAtual;
	}
	
	
}
