package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import DAO.Producao;
import Model.ModelEstatisticas;
import Views.ViewUnidades;

public class ControllerEstatisticas {
	private ModelEstatisticas model;
	
	public ControllerEstatisticas()
	{
		model = new ModelEstatisticas();
	}
	
	public ArrayList<Producao> buscarEstatisticas(String data, int tipoFiltro) {
		
		switch (tipoFiltro) {
		case 1:
			return model.buscarEstatisticasIgual(data);
		case 2:
			return model.buscarEstatisticasMaiorQue(data);
		case 3:
			return model.buscarEstatisticasMenorQue(data);
		default:
			break;
		}
		return null;
		
	}

	public void salvarCSV(ArrayList<Producao> listProducao) {
		ViewUnidades viewUnidades = new ViewUnidades();
		if (!viewUnidades.path.isEmpty())
		{
			Date data = new Date(System.currentTimeMillis());  
			SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy"); 
			String sData = formatarDate.format(data);
			try {
				FileWriter fw = new FileWriter(viewUnidades.path + "RelatorioDeProducao_" + sData + ".csv");
				fw.append("Relatorio de Producao - Equipamento ClaraMaq" + Character.toString('\n'));
				fw.append("Data deste documento:;" + sData + Character.toString('\n'));
				fw.append(Character.toString('\n'));
				fw.append( "Id Producao;Produto;Data da Producao;Meta;Tipo de Producao;Produzido;Tempo de Producao;Media de Producao" + Character.toString('\n'));
				for (Producao producao : listProducao) {
					fw.append(
							String.valueOf(producao.getId()) + ";" +
							producao.getNomeProduto() + ";" +
							producao.getDataProducao() + ";" +
							String.valueOf(producao.getMeta()) + ";" +
							producao.getTipoProducao() + ";" +
							String.valueOf(producao.getProduzido()) + ";" +
							String.valueOf(producao.getTempoProducao()) + ";" +
							String.valueOf(producao.getMediaProducao()) + ";" +
							Character.toString('\n')
							);
				}
				fw.flush();
				fw.close();
				
				JOptionPane.showMessageDialog(null, "Arquivo gravado com sucesso");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Houve um erro: " + e.getMessage());
			}
		}
	}

}
