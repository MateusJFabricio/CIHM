package Model;

import java.util.ArrayList;

import DAO.Producao;

public class ModelEstatisticas {

	public ArrayList<Producao> buscarEstatisticasIgual(String data) {
		return Main.Main.mannBD.mannProducao.getRegistrosDataIgual(data);
	}

	public ArrayList<Producao> buscarEstatisticasMaiorQue(String data) {
		return Main.Main.mannBD.mannProducao.getRegistrosDataMaiorQue(data);
	}

	public ArrayList<Producao> buscarEstatisticasMenorQue(String data) {
		return Main.Main.mannBD.mannProducao.getRegistrosDataMenorQue(data);
	}

}
